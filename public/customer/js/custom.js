/* =================== Load Function =================== */
$(window).load(function() {
	"use strict";
	/* ----------- Page Loader ----------- */
	$(".loader-item").delay(700).fadeOut();
	$("#pageloader").delay(1000).fadeOut("slow");
	/* ----------- Slanting Divs ----------- */
	slantSection();
	/* ----------- Pretty Photo ----------- */
	$("a[data-rel^='prettyPhoto']").prettyPhoto({
 		theme: "light_square",
		hook: 'data-rel'
 	});
	
	/* ----------- Blog Flexslider ----------- */
	$('.flexslider').flexslider({
		animation: "fade",
		prevText: "", 
		nextText: "", 
		directionNav: true
  	});
	
	/* ----------- Partners Section ----------- */
	PartnersSlider_Init();
	/* ----------- Reviews Section ----------- */
	ReviewSlider_Init();
	/* ----------- fractional slider ----------- */
	if( $('.fra-slider').length !== 0 ) {
		$('.fra-slider').fractionSlider({
			'fullWidth': 			true,
			'controls': 			true, 
			'pager': 				true,
			'responsive': 			true,
			'dimensions': 			"1230,470",
			'increase': 			false,
			'slideEndAnimation': 	true
		});
	}
	
});
/* =================== Load Function Ends =================== */

/* =================== Resize Function =================== */
$(window).resize(function() {  
	"use strict";
	/* ----------- Slanting Divs ----------- */
	slantSection();
	/* ----------- Partners Section ----------- */
	PartnersSlider_Init();
	/* ----------- Reviews Section ----------- */
	ReviewSlider_Init();
});
/* =================== Resize Function Ends =================== */

/* =================== Ready Function =================== */
$(document).ready(function() {
	/* ----------- Slanting Divs ----------- */
	$(function() {
		"use strict";
		slantSection();
	});
	/* ----------- Scroll Navigation ----------- */
	$(function() {
		"use strict";
		$('.scroll').bind('click', function(event) {
			var $anchor = $(this);
			var headerH = $('#navigation').outerHeight();
				$('html, body').stop().animate({					
					scrollTop : $($anchor.attr('href')).offset().top  - 45 + "px"
				}, 1200, 'easeInOutExpo');		
			event.preventDefault();
		});
	});
	
	/* ----------- Active Navigation ----------- */		
	$(function() {
		"use strict";
		$('body').scrollspy({ 
			target: '#topnav',
			offset: 95
		});
	});
	
	
	/* ----------- Menus hide after click -- mobile devices ----------- */	
	$(function() {
		"use strict";
		$(document).on( 'click', '.nav li a', function(e){
			$('.navbar-collapse').removeClass('in');
		});
	});
	
	/* ----------- Fixed Menu on Scroll	----------- */
	$(function() {
		"use strict";
		$("#sticky-section").sticky({topSpacing:0});
	});
	/* ----------- Sidenav -----------*/	
	$(document).on( 'click', '#navigation-menu', function(e){
		"use strict";
		e.preventDefault();		
		$("#wrapper .toggle-menu").animate({ right: '0px' }, "slow");		
		return false;
	});	
	$(document).on( 'click', '#navigation-close', function(e){	
		"use strict";
		e.preventDefault();		
		$("#wrapper .toggle-menu").animate({ right: '-35%' }, "slow");		
		return false;
	});	
	/* ----------- Sidenav hides after click ----------- */	
	$(function() {
		"use strict";
		$(document).on( 'click', '#wrapper .nav li a', function(e){
			$("#wrapper .toggle-menu").animate({ right: '-35%' }, "slow");		
			return false;
		});
	});
	/* ----------- Animation ----------- */
	$(function() {
		"use strict";
		$('.animated').appear(function() {
			var elem = $(this);
			var animation = elem.data('animation');
			if ( !elem.hasClass('visible') ) {
				var animationDelay = elem.data('animation-delay');
				if ( animationDelay ) {			
					setTimeout(function(){
					 elem.addClass( animation + " visible" );
					}, animationDelay);				
				} else {
					elem.addClass( animation + " visible" );
				}
			}
		});
	});
	/* ========== Background image height equal to the browser height.==========*/
	$('#slides,#fixed_video1,.subscribe-block').css({ 'height': $(window).height() });
  	 $(window).on('resize', function() {
		"use strict";
        $('#slides,#fixed_video1,.subscribe-block').css({ 'height': $(window).height() });
   });
	/* --------------------------------------------
	Home Background Super Slider 
	-------------------------------------------- */
	$(function() {
		"use strict";
		if( $( "#slides" ).length !== 0 ) {
			$('#slides').superslides({
				animation: 'fade',
			});
		}
	});
	/* -----------------Typed Text Slider-------------- */
	$(".element").each(function(){
		"use strict";
		var $this = $(this);
		$this.typed({
		strings: $this.attr('data-elements').split(','),
		typeSpeed: 100, // typing speed
		backDelay: 3000 // pause before backspacing
		});
	});
	/* --------------------------------------------
	Text Slider
	-------------------------------------------- */		
	$(function() {
		"use strict";
		if ( $( ".text-slider" ).length !== 0 ) {
			$('.text-slider').easyTicker({
				direction: 'up',  
				speed: 'slow',
				interval: 4000,
				height: 'auto',
				visible: 1,
				mousePause: 0
			});
		}
	});
	/* ----------- Progress Bar ----------- */ 
	$(function() {
		"use strict";
		$('.progress-bar').each(function() {
			$(this).appear(function(){
			 var datavl = $(this).attr('data-percentage');
			 $(this).animate({ "width" : datavl + "%"}, '1200');
			});
		});
	});
	
	/* =================== Owl Carousel Slider =================== */	
	$(function() {
		"use strict";
		/* ----------- Your Work Section ----------- */
		$("#your-work-carousel").owlCarousel({
			items : 4,
			lazyLoad : true,
			autoPlay: false,
			navigation : true,
			navigationText: ['<i class="fa fa-angle-left"></i>','<i class="fa fa-angle-right"></i>'],		
			pagination: true,
			itemsCustom : false,
			itemsDesktop : [1199, 4],
			itemsDesktopSmall : [980, 4],
			itemsTablet : [768, 2],
			itemsTabletSmall : [480, 1],
			itemsMobile : [479, 1]
		});
		/* ----------- review section ----------- */
		$("#review-carousel").owlCarousel({
			autoPlay: false,
			navigation : true,
			navigationText: ['<i class="fa fa-angle-left"></i>','<i class="fa fa-angle-right"></i>'],		
			pagination: true,
			singleItem:true
		});

		/* ----------- Partners Section ----------- */
		PartnersSlider_Init();

		/* ----------- Pricing Section ----------- */
		$("#pricing-slider").owlCarousel({
			items : 4,
			lazyLoad : true,
			autoPlay: true,
			navigation : true,
			navigationText: ['<i class="fa fa-angle-left"></i>','<i class="fa fa-angle-right"></i>'],		
			pagination: false,
			itemsCustom : false,
			itemsDesktop : [1199, 3],
			itemsDesktopSmall : [980, 3],
			itemsTablet : [768, 2],
			itemsTabletSmall : [480, 1],
			itemsMobile : [479, 1]
		});

		/* ----------- Team Section ----------- */
		$("#team-slider").owlCarousel({
			items : 3,
			lazyLoad : true,
			autoPlay: false,
			navigation : true,
			navigationText: ['<i class="fa fa-angle-left"></i>','<i class="fa fa-angle-right"></i>'],		
			pagination: false,
			itemsCustom : false,
			itemsDesktop : [1199, 3],
			itemsDesktopSmall : [980, 3],
			itemsTablet : [768, 2],
			itemsTabletSmall : [480, 1],
			itemsMobile : [479, 1]
		});
		
		/* ----------- Blog Section ----------- */
		$("#blog-slider").owlCarousel({
			items : 4,
			lazyLoad : true,
			autoPlay: false,
			navigation : true,
			navigationText: ['<i class="fa fa-angle-left"></i>','<i class="fa fa-angle-right"></i>'],		
			pagination: false,
			itemsCustom : false,
			itemsDesktop : [1199, 2],
			itemsDesktopSmall : [980, 2],
			itemsTablet : [768, 2],
			itemsTabletSmall : [480, 1],
			itemsMobile : [479, 1]
		});

		/* ----------- Project Section ----------- */	
		$("#single-project-carousel").owlCarousel({
			items : 1,
			lazyLoad : true,
			autoPlay:  true,
			navigation : true,
			navigationText: ['<i class="fa fa-angle-left"></i>','<i class="fa fa-angle-right"></i>'],		
			pagination: true,
			itemsCustom : false,
			itemsDesktop : [1199, 1],
			itemsDesktopSmall : [980, 1],
			itemsTablet : [768, 1],
			itemsTabletSmall : [480, 1],
			itemsMobile : [479, 1]
		});

		/* ----------- Skill Section ----------- */	
		$("#skill-slider").owlCarousel({
			items : 5,
			lazyLoad : true,
			autoPlay: true,
			navigation : true,
			navigationText: ['<i class="fa fa-angle-left"></i>','<i class="fa fa-angle-right"></i>'],		
			pagination: false,
			itemsCustom : false,
			itemsDesktop : [1199, 3],
			itemsDesktopSmall : [980, 3],
			itemsTablet : [768, 3],
			itemsTabletSmall : [640, 2],
			itemsMobile : [479, 1]
		});
	});
	
	/* ----------- Comment Form ----------- */	
	$(function() {
		"use strict";
		$('#commentform').bootstrapValidator({
				container: 'tooltip',
				feedbackIcons: {
					valid: 'fa fa-check',
					warning: 'fa fa-user',
					invalid: 'fa fa-times',
					validating: 'fa fa-refresh'
				},
				fields: {            
					contact_name: {
						validators: {
							notEmpty: {
								message: 'Name is required. Please enter name.'
							}
						}
					},
					contact_email: {
						validators: {
							notEmpty: {
								message: 'Email is required. Please enter email.'
							},
							emailAddress: {
								message: 'Please enter a correct email address.'
							}
						}
					},	
					contact_number: {
						validators: {
							notEmpty: {
								warning: {
								message: 'Phone Number is required. Please enter Your Contact Number.'
							},
							emailAddress: {
								message: 'Please enter a correct email address.'
								}
							}
						}
					},	
					contact_message: {
						validators: {
							notEmpty: {
								message: 'Message is required. Please enter your message.'
							}                    
						}
					}
				}
			})	
				
			.on('success.form.bv', function(e) {
						
			var data = $('#contactform').serialize();
			var contact_email=document.getElementById("contact_email").value;
			var contact_number =document.getElementById("contact_number").value;
			var contact_name =document.getElementById("contact_name").value;
			var contact_message =document.getElementById("contact_message").value;
			$.ajax({
					type: "POST",
					url: "process.php",					
					data: {'contact_email': contact_email , 'contact_number': contact_number,'contact_name':contact_name,'contact_message':contact_message },
					success: function(msg){						
						$('.comment-message').html(msg);
						$('.comment-message').show();
						contact_name="";
						contact_number="";
						contact_name="";
						contact_message="";
						
						
						//submitButton.removeAttr("disabled");
						resetForm($('#commentform'));						
					},
					error: function(msg){						
						$('.comment-message').html(msg);
						$('.comment-message').show();
						submitButton.removeAttr("disabled");
						resetForm($('#commentform'));
					}
			 });
			 
			return false;
		});
			
		/*Register Form*/	
		$('#registerform').bootstrapValidator({
				container: 'tooltip',
				feedbackIcons: {
					valid: 'fa fa-check',
					warning: 'fa fa-user',
					invalid: 'fa fa-times',
					validating: 'fa fa-refresh'
				},
				fields: {            
					contact_name: {
						validators: {
							notEmpty: {
								message: 'Name is required. Please enter name.'
							}
						}
					},
					contact_email: {
						validators: {
							notEmpty: {
								message: 'Email is required. Please enter email.'
							},
							emailAddress: {
								message: 'Please enter a correct email address.'
							}
						}
					},	
					contact_phone: {
						validators: {
							notEmpty: {
								warning: {
								message: 'Phone Number is required. Please enter Your Contact Number.'
							},
							emailAddress: {
								message: 'Please enter a correct email address.'
								}
							}
						}
					},	
				}
			})	
				
			.on('success.form.bv', function(e) {
						
			var data = $('#registerform').serialize();
			var contact_email=document.getElementById("contact_email").value;
			var contact_phone =document.getElementById("contact_phone").value;
			var contact_name =document.getElementById("contact_name").value;
			$.ajax({
					type: "POST",
					url: "process.php",					
					data: {'contact_email': contact_email , 'contact_phone': contact_phone,'contact_name':contact_name },
					success: function(msg){						
						$('.register-message').html(msg);
						$('.register-message').show();
						contact_name="";
						contact_phone="";
						contact_name="";
						
						
						//submitButton.removeAttr("disabled");
						resetForm($('#registerform'));						
					},
					error: function(msg){						
						$('.register-message').html(msg);
						$('.register-message').show();
						submitButton.removeAttr("disabled");
						resetForm($('#registerform'));
					}
			 });
			 
			return false;
		});	
			
			
		$('#subscribe').bootstrapValidator({
			container: 'tooltip',
			feedbackIcons: {
				valid: 'fa fa-check',
				warning: 'fa fa-user',
				invalid: 'fa fa-times',
				validating: 'fa fa-refresh'
			},
			fields: { 
				subscribe_email: {
					validators: {
						notEmpty: {
							message: 'Email is required. Please enter email.'
						},
						emailAddress: {
							message: 'Please enter a correct email address.'
						}
					}
				},	
			}
		})	
			
		.on('success.form.bv', function(e) {
					
			e.preventDefault();
   
			var $form        = $(e.target),
			validator    = $form.data('bootstrapValidator'),
			submitButton = validator.getSubmitButton();
			var form_data = $('#subscribe').serialize();
			
			$.ajax({
					type: "POST",
					dataType: 'json',
					url: "subscription.php",					
					data: form_data,
					success: function(msg){
						$('.form-message1').html(msg.data);
						$('.form-message1').show();
						
						submitButton.removeAttr("disabled");
						resetForm($('#subscribe'));						
					},
					error: function(msg){}
			 });
			 
			return false;
		});
		
		function resetForm($form) {
			$form.find('input:text, input:password, input, input:file, select, textarea').val('');
			$form.find('input:radio, input:checkbox').removeAttr('checked').removeAttr('selected');
			$form.find('.form-control-feedback').css('display', 'none');
		}
	});
	/* ----------- Video ----------- */	
	$(function() {
		"use strict";
		if ( $('.player').length !== 0 ) {
			$('.player').mb_YTPlayer();	
		}
	});
	
});


/* ============================= Carousel Slider ============================= */	
/* ----------- Partners Section ----------- */
function PartnersSlider_Init() {
	var width = $(window).width();
	if( width > 480 ) {  
		$("#partners-slider").owlCarousel({
			items : 5,
			lazyLoad : true,
			autoPlay: false,
			navigation : true,
			navigationText: ['<i class="fa fa-angle-left"></i>','<i class="fa fa-angle-right"></i>'],  
			pagination: false,
			itemsCustom : false,
			itemsDesktop : [1199, 5],
			itemsDesktopSmall : [980, 5],
			itemsTablet : [768, 3],
			itemsTabletSmall : [640, 2],
			itemsMobile : [480, 1]
		});
	}
}

/* ----------- Reviews Section ----------- */
function ReviewSlider_Init() {
	$("#reviewslider").owlCarousel({
		items :1,
		lazyLoad : false,
		autoPlay: true,
		navigation : true,
		navigationText: ['<i class="fa fa-angle-left"></i>','<i class="fa fa-angle-right"></i>'],  
		pagination: false,
		itemsCustom : false,
		itemsDesktop : [1199, 1],
		itemsDesktopSmall : [980, 1],
		itemsTablet : [768, 1],
		itemsTabletSmall : [480, 1],
		itemsMobile : [479, 1]
	});
}

/* ----------- Slanting Div's ----------- */
var slantAngle = 3;
var valAlpha = slantAngle * (Math.PI / 180);
var valBeta = (90 - slantAngle) * (Math.PI / 180);

function slantSection() {
	var slantHeight = ($(window).width() * Math.sin(valAlpha))/ Math.sin(valBeta);
	var slantWidth = $(window).width();
	$('.slant-top-angle').each(function() {		
		$(this).css('border-top-width', + slantHeight + 'px ');
		$(this).css('border-left-width', + slantWidth + 'px');
	});	
	$('.slant-bottom-angle').each(function() {
		var slantPosition = $(this).parent('.slant-angle').outerHeight() - slantHeight + 2;		
		$(this).css('top', + slantPosition + 'px ');	
		$(this).css('border-bottom-width', + slantHeight + 'px ');
		$(this).css('border-right-width', + slantWidth + 'px');
	});	
};
/* Color Panel */
$(document).ready(function() {
	"use strict";
     $("#theme-panel").show();
});