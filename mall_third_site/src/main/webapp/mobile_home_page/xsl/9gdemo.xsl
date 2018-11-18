<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<!-- 定义xml样式 -->
	<xsl:template match="/">
		<xsl:apply-templates/>
	</xsl:template>
	
	<xsl:template match="app_cont">
		<div class="app_item app_cube" style="display: block;">
        	<div class="app_cont item" style="height: 320px;">
        		<xsl:attribute name="id">app_cont<xsl:value-of select="@id" /></xsl:attribute>
				<xsl:apply-templates select="a"/>
			</div>
            <div class="app_edit" style="width: 100%; height: 100%;">
            	<div class="app_btns">
                    <a href="javascript:;" class="edit">
                    	<xsl:attribute name="onclick">
							updatemf(<xsl:value-of select="@id" />)
						</xsl:attribute>
                    	编辑
                    </a>
                    <a href="javascript:;" class="delete">
                    	<xsl:attribute name="onclick">
							delmf(<xsl:value-of select="@id" />)
						</xsl:attribute>
                    	删除
                    </a>
                </div>
            </div>
        </div>
	</xsl:template>
	
	<xsl:template match="img_adv">
		<div class="app_item app_cube" style="display: block;">
			<div class="img_adv item">
        		<xsl:attribute name="id">img_adv_<xsl:value-of select="@id" /></xsl:attribute>
				<xsl:apply-templates select="a"/>
			</div>
            <div class="app_edit" style="width: 100%; height: 100%;">
            	<div class="app_btns">
                    <a href="javascript:;" class="edit">
                    	<xsl:attribute name="onclick">
							updateAdv('<xsl:value-of select="@id" />')
						</xsl:attribute>
                    	编辑
                    </a>
                    <a href="javascript:;" class="delete">
                    	<xsl:attribute name="onclick">
							delAdv('<xsl:value-of select="@id" />')
						</xsl:attribute>
                    	删除
                    </a>
                </div>
            </div>
        </div>
	</xsl:template>
	
	<xsl:template match="roll_adv">
		<div class="app_item app_cube" style="display: block;">
			<div class="roll_adv item">
        		<xsl:attribute name="id">roll_adv<xsl:value-of select="@id" /></xsl:attribute>
				<div name="roll_banner" class="roll_banner">
					<div class="swiper-container">
						<div class="swiper-wrapper">
							<xsl:apply-templates select="roll_a"/>
						</div>
						<div class="swiper-pagination"></div>
					</div>
				</div>
        	</div>
            <div class="app_edit" style="width: 100%; height: 100%;">
            	<div class="app_btns">
                    <a href="javascript:;" class="edit">
                    	<xsl:attribute name="onclick">
							updateRollAdv('<xsl:value-of select="@id" />')
						</xsl:attribute>
                    	编辑
                    </a>
                    <a href="javascript:;" class="delete">
                    	<xsl:attribute name="onclick">
							delRollAdv('<xsl:value-of select="@id" />')
						</xsl:attribute>
                    	删除
                    </a>
                </div>
            </div>
        </div>
	</xsl:template>
	
	<xsl:template match="fullRoll">
		<div class="app_item app_cube" style="display: block;">
			<div class="fullRoll item">
        		<xsl:attribute name="id">fullRoll<xsl:value-of select="@id" /></xsl:attribute>
				<input type="hidden" class="fullRollSD">
	        		<xsl:attribute name="value"><xsl:value-of select="@sd" /></xsl:attribute>
				</input>
				<div class="phone_wp pr">
					<div class="full-swiper-container">
						<div class="swiper-wrapper fc_slides">
							<xsl:apply-templates select="roll_a"/>        	
						</div>
						<div class="full-pagination"></div>
					</div>
					<b class="music-ico">
						<xsl:if test="@music != ''">
						<xsl:attribute name="style">display:inline;</xsl:attribute>
						<audio><xsl:attribute name="src"><xsl:value-of select="@music" /></xsl:attribute></audio>
						</xsl:if>
					</b>
					<input type="hidden" class="musicname">
		        		<xsl:attribute name="value"><xsl:value-of select="@musicname" /></xsl:attribute>
					</input>
					<b class="vtc-img">
						<xsl:if test="@sd = 'sv'">
						<xsl:attribute name="style">display:inline;</xsl:attribute>
						</xsl:if>
					</b>
				</div>
        	</div>
            <div class="app_edit" style="width: 100%; height: 100%;">
            	<div class="app_btns">
                    <a href="javascript:;" class="edit">
                    	<xsl:attribute name="onclick">
							updateFullRoll('<xsl:value-of select="@id" />')
						</xsl:attribute>
                    	编辑
                    </a>
                    <a href="javascript:;" class="delete">
                    	<xsl:attribute name="onclick">
							delFullRoll('<xsl:value-of select="@id" />')
						</xsl:attribute>
                    	删除
                    </a>
                </div>
            </div>
        </div>
	</xsl:template>
	
	<xsl:template match="text">
		<div class="app_item app_cube" style="display: block;">
			<div class="text item">
        		<xsl:attribute name="id">text_app<xsl:value-of select="@id" /></xsl:attribute>
				<textarea style="display:none">
					<xsl:attribute name="id">textCont<xsl:value-of select="@id" /></xsl:attribute>
					<xsl:value-of select="." />
				</textarea>
				
        	</div>
            <div class="app_edit" style="width: 100%; height: 100%;">
            	<div class="app_btns">
                    <a href="javascript:;" class="edit">
                    	<xsl:attribute name="onclick">
							updateTextEdit('<xsl:value-of select="@id" />')
						</xsl:attribute>
                    	编辑
                    </a>
                    <a href="javascript:;" class="delete">
                    	<xsl:attribute name="onclick">
							delText('<xsl:value-of select="@id" />')
						</xsl:attribute>
                    	删除
                    </a>
                </div>
            </div>
        </div>
	</xsl:template>
	
	<xsl:template match="goodsmod">
		<div class="app_item" style="display: block;">
			<div class="goodsmod item">
        		<xsl:attribute name="id">goodsmod<xsl:value-of select="@id" /></xsl:attribute>
        		<xsl:attribute name="styleClass"><xsl:value-of select="@class" /></xsl:attribute>
				<ul class="app-goods clearfix">
					<xsl:attribute name="class">app-goods clearfix <xsl:value-of select="@class" /></xsl:attribute>
					<xsl:apply-templates select="li"/>
				</ul>
        	</div>
            <div class="app_edit" style="width: 100%; height: 100%;">
            	<div class="app_btns">
                    <a href="javascript:;" class="edit">
                    	<xsl:attribute name="onclick">
							updateGoods('<xsl:value-of select="@id" />')
						</xsl:attribute>
                    	编辑
                    </a>
                    <a href="javascript:;" class="delete">
                    	<xsl:attribute name="onclick">
							delGoods('<xsl:value-of select="@id" />')
						</xsl:attribute>
                    	删除
                    </a>
                </div>
            </div>
        </div>
	</xsl:template>
	
	<xsl:template match="blankbox">
		<div class="app_item" style="display: block;">
			<div class="blank-box item">
        		<xsl:attribute name="id">blankbox<xsl:value-of select="@id" /></xsl:attribute>
        		<xsl:attribute name="style">height:<xsl:value-of select="@height" />px</xsl:attribute>
			</div>
            <div class="app_edit" style="width: 100%; height: 100%;">
            	<div class="app_btns">
                    <a href="javascript:;" class="edit">
                    	<xsl:attribute name="onclick">
							updateBlankbox('<xsl:value-of select="@id" />')
						</xsl:attribute>
                    	编辑
                    </a>
                    <a href="javascript:;" class="delete">
                    	<xsl:attribute name="onclick">
							delBlankbox('<xsl:value-of select="@id" />')
						</xsl:attribute>
                    	删除
                    </a>
                </div>
            </div>
        </div>
	</xsl:template>
	<xsl:template match="dividing">
		<div class="app_item" style="display: block;">
			<div class="dividing item">
        		<xsl:attribute name="id">dividing<xsl:value-of select="@id" /></xsl:attribute>
        		<div class="line-wp">
        			<hr class="line-app"/>
        		</div>
			</div>
            <div class="app_edit" style="width: 100%; height: 100%;">
            	<div class="app_btns">
                    <a href="javascript:;" class="delete">
                    	<xsl:attribute name="onclick">
							delLine('<xsl:value-of select="@id" />')
						</xsl:attribute>
                    	删除
                    </a>
                </div>
            </div>
        </div>
	</xsl:template>
	 
	<xsl:template match="li">
		<li>
			<xsl:apply-templates select="goods_a"/>
		</li>
	</xsl:template>
	
	<xsl:template match="goods_a">
		<a href="#">
			<xsl:attribute name="href"><xsl:value-of select="@href" /></xsl:attribute>
			<xsl:apply-templates select="img"/>
			<div class="goods-info">
				<h3><xsl:value-of select="@name" /></h3>
				<span class="good-price"><xsl:value-of select="@price" /></span>
				<em class="good-buy-btn">立即购买</em>
			</div>
		</a>
	</xsl:template>
	 
	<xsl:template match="roll_a">
		<div class="swiper-slide">
		<a href="#">
			<xsl:attribute name="href">
				<xsl:value-of select="@href" />
			</xsl:attribute>
			<xsl:apply-templates select="img"/>
		</a>
		</div>
	</xsl:template>
	
	<xsl:template match="a">
		<a href="#">
			<xsl:attribute name="href">
				<xsl:value-of select="@href" />
			</xsl:attribute>
			<xsl:apply-templates select="img"/>
		</a>
	</xsl:template>
	<xsl:template match="img">
		<img>
			<xsl:attribute name="id">sa<xsl:value-of select="@id" /></xsl:attribute>
			<xsl:attribute name="src">
				<xsl:value-of select="@src" />
			</xsl:attribute>
			<xsl:attribute name="width">
				<xsl:value-of select="@width" />
			</xsl:attribute>
			<xsl:attribute name="height">
				<xsl:value-of select="@height" />
			</xsl:attribute>
			<xsl:attribute name="class">
				<xsl:value-of select="@class" />
			</xsl:attribute>
			<xsl:attribute name="style">
				<xsl:value-of select="@style" />
			</xsl:attribute>
		</img>
	</xsl:template>
</xsl:stylesheet>

