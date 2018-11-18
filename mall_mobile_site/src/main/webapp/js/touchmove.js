/**
 * Created by Raby on 2015/8/4.
 */
var pathment = document.getElementById('PathMenu');
$('.Tmain').attr('onclick','PathRun(4)');
pathment.addEventListener('touchmove', function(event) {
  event.preventDefault();//阻止其他事件

  // 如果这个元素的位置内只有一个手指的话
  if (event.targetTouches.length == 1) {
    var touch = event.targetTouches[0];  // 把元素放在手指所在的位置
    pathment.style.left = touch.clientX + 'px';
    pathment.style.top = touch.clientY + 'px';
  }
}, false);
pathment.addEventListener('touchend', function(event) {
  if(parseInt(pathment.style.left) < $(window).width()/2){
    pathment.style.left = '25px';
    if(parseInt(pathment.style.top) < 140){
      $('.Tmain').attr('onclick','PathRun(2)');
    }
    else{
      $('.Tmain').attr('onclick','PathRun(1)');
    }
  }
  else{
    pathment.style.left = 'auto';
    pathment.style.right = '-2px';
    if(parseInt(pathment.style.top) < 140){
      if(parseInt(pathment.style.top) == 80){return;}
      $('.Tmain').attr('onclick','PathRun(3)');
    }
    else{
      $('.Tmain').attr('onclick','PathRun(4)');
    }
  }

}, false);