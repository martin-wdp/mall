/**
 * 
 */
$(function(){
	$( "button:first" ).button({
	      icons: {
	        primary: "ui-icon-plus"
	      }
	    }).next().button({
	      icons: {
	        primary: "ui-icon-pencil"
	      }
	    }).next().button({
	      icons: {
	        primary: "ui-icon-minus",
	      }
	    }).next().button({
	      icons: {
	        primary: "ui-icon-circle-minus",
	      }
	    });
});
