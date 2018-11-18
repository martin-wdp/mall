/**
 * IK 中文分词  版本 5.0
 * IK Analyzer release 5.0
 * 
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * 源代码由林良益(linliangyi2005@gmail.com)提供
 * 版权声明 2012，乌龙茶工作室
 * provided by Linliangyi and copyright 2012 by Oolong studio
 * 
 */
package com.ningpai.common.lucene.ikanalyzer.org.wltea.analyzer.core;


/**
 * Lexeme链（路径）
 */
class LexemePath extends QuickSortSet implements Comparable<LexemePath>{
    
    /**
     *起始位置
     */
    private int pathBegin;
    /**
     *结束
     */
    private int pathEnd;
    /**
     *词元链的有效字符长度
     */
    private int payloadLength;

    /**
     * 构造函数
     * */
    LexemePath(){
        this.pathBegin = -1;
        this.pathEnd = -1;
        this.payloadLength = 0;
    }

    /**
     * 向LexemePath追加相交的Lexeme
     * @param lexeme
     * @return 
     */
    boolean addCrossLexeme(Lexeme lexeme){
        if(this.isEmpty()){
            this.addLexeme(lexeme);
            this.pathBegin = lexeme.getBegin();
            this.pathEnd = lexeme.getBegin() + lexeme.getLength();
            this.payloadLength += lexeme.getLength();
            return true;
            
        }else if(this.checkCross(lexeme)){
            this.addLexeme(lexeme);
            if(lexeme.getBegin() + lexeme.getLength() > this.pathEnd){
                this.pathEnd = lexeme.getBegin() + lexeme.getLength();
            }
            this.payloadLength = this.pathEnd - this.pathBegin;
            return true;
            
        }else{
            return  false;
            
        }
    }
    
    /**
     * 向LexemePath追加不相交的Lexeme
     * @param lexeme
     * @return 
     */
    boolean addNotCrossLexeme(Lexeme lexeme){
        if(this.isEmpty()){
            this.addLexeme(lexeme);
            this.pathBegin = lexeme.getBegin();
            this.pathEnd = lexeme.getBegin() + lexeme.getLength();
            this.payloadLength += lexeme.getLength();
            return true;
            
        }else if(this.checkCross(lexeme)){
            return  false;
            
        }else{
            this.addLexeme(lexeme);
            this.payloadLength += lexeme.getLength();
            Lexeme head = this.peekFirst();
            this.pathBegin = head.getBegin();
            Lexeme tail = this.peekLast();
            this.pathEnd = tail.getBegin() + tail.getLength();
            return true;
            
        }
    }
    
    /**
     * 移除尾部的Lexeme
     * @return
     */
    Lexeme removeTail(){
        Lexeme tail = this.pollLast();
        if(this.isEmpty()){
            this.pathBegin = -1;
            this.pathEnd = -1;
            this.payloadLength = 0;            
        }else{        
            this.payloadLength -= tail.getLength();
            Lexeme newTail = this.peekLast();
            this.pathEnd = newTail.getBegin() + newTail.getLength();
        }
        return tail;
    }
    
    /**
     * 检测词元位置交叉（有歧义的切分）
     * @param lexeme
     * @return
     */
    boolean checkCross(Lexeme lexeme){
        return (lexeme.getBegin() >= this.pathBegin && lexeme.getBegin() < this.pathEnd)
                || (this.pathBegin >= lexeme.getBegin() && this.pathBegin < lexeme.getBegin()+ lexeme.getLength());
    }
    
    int getPathBegin() {
        return pathBegin;
    }

    int getPathEnd() {
        return pathEnd;
    }

    /**
     * 获取Path的有效词长
     * @return
     */
    int getPayloadLength(){
        return this.payloadLength;
    }
    
    /**
     * 获取LexemePath的路径长度
     * @return
     */
    int getPathLength(){
        return this.pathEnd - this.pathBegin;
    }
    

    /**
     * X权重（词元长度积）
     * @return
     */
    int getXWeight(){
        int product = 1;
        Cell c = this.getHead();
        while( c != null && c.getLexeme() != null){
            product *= c.getLexeme().getLength();
            c = c.getNext();
        }
        return product;
    }
    
    /**
     * 词元位置权重
     * @return
     */
    int getPWeight(){
        int pWeight = 0;
        int p = 0;
        Cell c = this.getHead();
        while( c != null && c.getLexeme() != null){
            p++;
            pWeight += p * c.getLexeme().getLength() ;
            c = c.getNext();
        }
        return pWeight;        
    }

    /**
     * 复制
     * */
    LexemePath copy(){
        LexemePath theCopy = new LexemePath();
        theCopy.pathBegin = this.pathBegin;
        theCopy.pathEnd = this.pathEnd;
        theCopy.payloadLength = this.payloadLength;
        Cell c = this.getHead();
        while( c != null && c.getLexeme() != null){
            theCopy.addLexeme(c.getLexeme());
            c = c.getNext();
        }
        return theCopy;
    }

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * <p>The implementor must ensure <tt>sgn(x.compareTo(y)) ==
     * -sgn(y.compareTo(x))</tt> for all <tt>x</tt> and <tt>y</tt>.  (This
     * implies that <tt>x.compareTo(y)</tt> must throw an exception iff
     * <tt>y.compareTo(x)</tt> throws an exception.)
     *
     * <p>The implementor must also ensure that the relation is transitive:
     * <tt>(x.compareTo(y)&gt;0 &amp;&amp; y.compareTo(z)&gt;0)</tt> implies
     * <tt>x.compareTo(z)&gt;0</tt>.
     *
     * <p>Finally, the implementor must ensure that <tt>x.compareTo(y)==0</tt>
     * implies that <tt>sgn(x.compareTo(z)) == sgn(y.compareTo(z))</tt>, for
     * all <tt>z</tt>.
     *
     * <p>It is strongly recommended, but <i>not</i> strictly required that
     * <tt>(x.compareTo(y)==0) == (x.equals(y))</tt>.  Generally speaking, any
     * class that implements the <tt>Comparable</tt> interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     *
     * <p>In the foregoing description, the notation
     * <tt>sgn(</tt><i>expression</i><tt>)</tt> designates the mathematical
     * <i>signum</i> function, which is defined to return one of <tt>-1</tt>,
     * <tt>0</tt>, or <tt>1</tt> according to whether the value of
     * <i>expression</i> is negative, zero or positive.
     *
     * @param   o the object to be compared.
     * @return  a negative integer, zero, or a positive integer as this object
     *          is less than, equal to, or greater than the specified object.
     *
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException if the specified object's type prevents it
     *         from being compared to this object.
     */
    public int compareTo(LexemePath o) {
        //比较有效文本长度
        if(this.payloadLength > o.payloadLength){
            return -1;
        }else if(this.payloadLength < o.payloadLength){
            return 1;
        }else{
            //比较词元个数，越少越好
            if(this.size() < o.size()){
                return -1;
            }else if (this.size() > o.size()){
                return 1;
            }else{
                //路径跨度越大越好
                if(this.getPathLength() >  o.getPathLength()){
                    return -1;
                }else if(this.getPathLength() <  o.getPathLength()){
                    return 1;
                }else {
                    //根据统计学结论，逆向切分概率高于正向切分，因此位置越靠后的优先
                    if(this.pathEnd > o.pathEnd){
                        return -1;
                    }else if(pathEnd < o.pathEnd){
                        return 1;
                    }else{
                        //词长越平均越好
                        if(this.getXWeight() > o.getXWeight()){
                            return -1;
                        }else if(this.getXWeight() < o.getXWeight()){
                            return 1;
                        }else {
                            //词元位置权重比较
                            if(this.getPWeight() > o.getPWeight()){
                                return -1;
                            }else if(this.getPWeight() < o.getPWeight()){
                                return 1;
                            }
                            
                        }
                    }
                }
            }
        }
        return 0;
    }
    /**
     * Returns a string representation of the object. In general, the
     * {@code toString} method returns a string that
     * "textually represents" this object. The result should
     * be a concise but informative representation that is easy for a
     * person to read.
     * It is recommended that all subclasses override this method.
     * <p>
     * The {@code toString} method for class {@code Object}
     * returns a string consisting of the name of the class of which the
     * object is an instance, the at-sign character `{@code @}', and
     * the unsigned hexadecimal representation of the hash code of the
     * object. In other words, this method returns a string equal to the
     * value of:
     * <blockquote>
     * <pre>
     * getClass().getName() + '@' + Integer.toHexString(hashCode())
     * </pre></blockquote>
     *
     * @return  a string representation of the object.
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("pathBegin  : ").append(pathBegin).append("\r\n");
        sb.append("pathEnd  : ").append(pathEnd).append("\r\n");
        sb.append("payloadLength  : ").append(payloadLength).append("\r\n");
        Cell head = this.getHead();
        while(head != null){
            sb.append("lexeme : ").append(head.getLexeme()).append("\r\n");
            head = head.getNext();
        }
        return sb.toString();
    }

}
