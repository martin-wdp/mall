(function( $ ) {
    $.widget( "custom.combobox", {
      _create: function() {
        this.wrapper = $( "<span>" )
          .addClass( "custom-combobox" )
          .insertAfter( this.element );
 
        this.element.hide();
        this._createAutocomplete();
        this._createShowAllButton();
      },
 
      _createAutocomplete: function() {
        var selected = this.element.children( ":selected" ),
          value = selected.val() ? selected.text() : "";
 
        this.input = $( "<input>" )
          .appendTo( this.wrapper )
          .val( value )
          .attr( "title", "" )
          .addClass( "custom-combobox-input ui-widget ui-widget-content ui-state-default ui-corner-left" )
          .autocomplete({
            delay: 0,
            minLength: 0,
            source: $.proxy( this, "_source" )
          })
          .tooltip({
            tooltipClass: "ui-state-highlight"
          });
 
        this._on( this.input, {
          autocompleteselect: function( event, ui ) {
            ui.item.option.selected = true;
            this._trigger( "select", event, {
              item: ui.item.option
            });
          },
 
          autocompletechange: "_removeIfInvalid"
        });
      },
 
      _createShowAllButton: function() {
        var input = this.input,
          wasOpen = false;
 
        $( "<a>" )
          .attr( "tabIndex", -1 )
          .attr( "title", "Show All Items" )
          .tooltip()
          .appendTo( this.wrapper )
          .button({
            icons: {
              primary: "ui-icon-triangle-1-s"
            },
            text: false
          })
          .removeClass( "ui-corner-all" )
          .addClass( "custom-combobox-toggle ui-corner-right" )
          .mousedown(function() {
            wasOpen = input.autocomplete( "widget" ).is( ":visible" );
          })
          .click(function() {
            input.focus();
 
            // Close if already visible
            if ( wasOpen ) {
              return;
            }
 
            // Pass empty string as value to search for, displaying all results
            input.autocomplete( "search", "" );
          });
      },
 
      _source: function( request, response ) {
        var matcher = new RegExp( $.ui.autocomplete.escapeRegex(request.term), "i" );
        response( this.element.children( "option" ).map(function() {
          var text = $( this ).text();
          if ( this.value && ( !request.term || matcher.test(text) ) )
            return {
              label: text,
              value: text,
              option: this
            };
        }) );
      },
 
      _removeIfInvalid: function( event, ui ) {
 
        // Selected an item, nothing to do
        if ( ui.item ) {
          return;
        }
 
        // Search for a match (case-insensitive)
        var value = this.input.val(),
          valueLowerCase = value.toLowerCase(),
          valid = false;
        this.element.children( "option" ).each(function() {
          if ( $( this ).text().toLowerCase() === valueLowerCase ) {
            this.selected = valid = true;
            return false;
          }
        });
 
        // Found a match, nothing to do
        if ( valid ) {
          return;
        }
 
        // Remove invalid value
        this.input
          .val( "" )
          .attr( "title", value + " didn't match any item" )
          .tooltip( "open" );
        this.element.val( "" );
        this._delay(function() {
          this.input.tooltip( "close" ).attr( "title", "" );
        }, 2500 );
        this.input.data( "ui-autocomplete" ).term = "";
      },
 
      _destroy: function() {
        this.wrapper.remove();
        this.element.show();
      }
    });
  })( jQuery );
  
  
  $(function() {
    $( "input[type=submit],button" )
      .button()
      .click(function( event ) {
        event.preventDefault();
      });
    $( "#combobox" ).combobox();
    $( "#toggle" ).click(function() {
      $( "#combobox" ).toggle();
    });
  
  //添加按钮
  $( "#create" )
  .button()
  .click(function() {
	  //跳转到显示资讯单页
	  location.href="showChannel.htm";
	  
  });
  
  
  
  //修改弹出框
  $( "#update" )
  .button()
  .click(function() {
	  
	  //判断是否有按钮选中
	  if(checkCombox()==false){
		  return false;
	  }
	  
	//判断是否选中多个按钮
	  if(oneCheck("channelId")==false){
		  return false;
	  }
	  
	  var channelId = $("input[type='checkbox']:checked");  
	  location.href="showChannel.htm?channelID="+channelId.val();
  });
  
  
  
	//删除弹出框
  $( "#delete" )
  .button()
  .click(function() {
	
	  //判断是否有按钮选中
	  if(checkCombox()==false){
		  return false;
	  }
	  
	//判断是否选中多个按钮
	  if(oneCheck("channelId")==false){
		  return false;
	  }
	  
	  $("#dialog-confirm").dialog("open");
  });
	
	//批量删除弹出框
  $( "#deleteAll" )
  .button()
  .click(function() {
	
	  //判断是否有按钮选中
	  if(checkCombox()==false){
		  return false;
	  }
	  
	  $("#dialog-confirm").dialog("open");
  });
	
  $("#dialog-confirm").dialog({
		resizable : false,
		height : 150,
		width : 270,
		modal : true,
		autoOpen : false,
		buttons : {
			"确定" : function() {
				//删除数据
				var checkedList = new Array();
				$("input[name='channelId']:checked").each(function() {
					checkedList.push($(this).val());
				});
		    	if(checkedList.length > 0){
		    		$.post('deleteChannel.htm',{channelIDs:checkedList},function(){  
		            	location.href="queryChannelByPageBean.htm";
		            });   
		        }  
	      		
			},
			'取消' : function() {
				$(this).dialog("close");
			}
		}
	});	
  	//设置全部开启和全部关闭的按钮样式
  	$( "#openAll" ).button({
      icons: {
        primary: "ui-icon-circle-check"
      }
    });
  	$( "#closeAll" ).button({
  		icons: {
  			primary: "ui-icon-circle-close"
  		}
  	});
  	$( "#searchBtn" ).button({
  		icons: {
  			primary: "ui-icon-search"
  		}
  	});
  });
//判断是否选中按钮
  function checkCombox(){
	  var channelId = $("input[type='checkbox']:checked");  
	  //判断是否选择
	  if(typeof(channelId.val())=="undefined"){
		  $("#dialog-tip").dialog(
				  {
					  resizable : false, height : 150, width : 270, modal : true, autoOpen : true, buttons : {
					      "确定" : function () 
					        {
					                $(this).dialog("close");
					        }
					 }
				 });
		  openDialog();
		  return false;
	  }else{
		  return true;
	  }
  }
  
  function openChannelById(id){
	  location="openChannelById.htm?CSRFToken="+$("#token").val()+"&channelId="+id;
  }
  function closeChannelById(id){
	  location="closeChannelById.htm?CSRFToken="+$("#token").val()+"&channelId="+id;
    //location.href="closeChannelById.htm?channelId="+id;
  }
  function openAllChannel(){
	  location="openAllChannel.htm";
  }
  function closeAllChannel(){
	  location="closeAllChannel.htm";
  }