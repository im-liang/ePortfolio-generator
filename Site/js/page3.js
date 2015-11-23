// CONSTANTS
var IMG_PATH;
var ICON_PATH;
var IMG_WIDTH;
var SCALED_IMAGE_HEIGHT;
var SLIDESHOW_SLEEP_TIME;
var FADE_TIME;

// DATA
var student_name;
var components;
var page_title;
var page_path;
var banner;
var footer;
var currentSlide;
var image_name;
var currentPage = 0;
var contentDataFile;

// TIMER FOR PLAYING SLIDESHOW
var timer;

function Component(initType, initName, initPath, initFont, initDesiredText) {
    this.component_type = initType;
    this.component_name = initName;
    this.component_path = initPath;
    this.component_font = initFont;
    this.component_desiredText = initDesiredText;
}

function initEPortfolio() {
    IMG_PATH = "./img/";
    ICON_PATH = "./icons/";
    IMG_WIDTH = 1000;
    SCALED_IMAGE_HEIGHT = 500.0;
    FADE_TIME = 1000;
    SLIDESHOW_SLEEP_TIME = 3000;
    components = new Array();
    page_title = new Array();
    page_path = new Array();
    contentDataFile = "./data/content3.json";
    // contentDataFile = new Array();
        image_caption = ["Sab", "Volvo", "BMW"];
    var indexDataFile = "./data/index.json";
    loadPageData(indexDataFile);
    loadData(contentDataFile);
    timer = null;
}
function loadPageData(jsonFile) {
    $.getJSON(jsonFile, function(jso) {
        json = jso;
        for(var i = 0; i < Object.keys(json.index).length; i++) {
            var title = json.index[i].page_title;
            var path = json.index[i].page_path;
            page_title.push(title);
            page_path.push(path);
        }
    });
}

function initPage() {
    $("#student_name").append("<p>" + student_name +"</p>");
    $("#student_name").append("<p>" + banner +"</p>");
    for(var i = 0; i < page_title.length; i++) {
        $("#nav_bar ul").append("<li><a href='" + page_path[i] + "'><span>" + page_title[i] + "</span></a></li>");
    }
	for(var i = 0; i < components.length; i++) {
        switch (components[i].component_type) {
            case 'image':
                $("#components").append("<br><img class = img src = '" + components[i].component_path + components[i].component_name + "'>");
                break;
            case 'video':
                $("#components").append("<br><video class = video_controls> <source src = '" + components[i].component_path + components[i].component_name + "'></video>");
                break;
            case 'list':
                var component_list = components[i].component_name;
                if(components[i].component_desiredText !== "") {
                    var list_text = components[i].component_desiredText.split(",");
                    var list_link = components[i].component_path.split(",");
                    for(var k = 0; k < list_text.length; k++) {
                        component_list.replace(list_text[k], "<a href ="+list_link[k]+">"+list_text[k]+"</a>");
                    }
                }
                component_list = components[i].component_name.split(",");
                $("#components").append("<br><ul class='list'>");
                for(var j = 0; j < component_list.length; j++) {
                    $("#components").append("<li>"+component_list[j]+"</li>");
                }
                $("#components").append("</ul>");
                break;
            case 'paragraph':
                $("#components").append("<br><div class = paragraph>" + components[i].component_name + "</div>");
                if(components[i].component_desiredText !== "") {
                    var p_text = components[i].component_desiredText.split(",");
                    for(var k = 0; k < p_text.length; k++) {
                        components[i].component_name.replace(text[k], "<a href ="+component_path+">"+p_text[k]+"</a>");
                    }
                }
                break;
            case 'slideshow':
                image_name = components[i].component_name.split(",");
                var image_caption = components[i].component_desiredText.split(",");
                if(image_name.length > 0) {
                    currentSlide = 0;
                    $("#components").append("<img id = slideshow class = img src = '" + components[i].component_path + image_name[currentSlide] + "'>");
                    $("#components").append("<div id = caption>" + image_caption[0] + "</div>");
                    $("#components").append("<div id = slideshow_controls>");
                    $("#components").append('<input id="previous_button" type="image" src="./icons/Previous.png" onclick="processPreviousRequest()">');
                    $("#components").append('<input id="play_pause_button" type="image" src="./icons/Play.png" onclick="processPlayPauseRequest()">');
                    $("#components").append('<input id="next_button" type="image" src="./icons/Next.png" onclick="processNextRequest()">');
                    $("#components").append("</div>");
                }
                break;
        }
    }
	$(".img").one("load", function() {
	    autoScaleImage();
	});
    $("#footer").html(footer);
}

function autoScaleImage() {
	// var origHeight = $(".img").height();
	// var scaleFactor = SCALED_IMAGE_HEIGHT/origHeight;
	// var origWidth = $(".img").width();
	// var scaledWidth = origWidth * scaleFactor;
	// $(".img").height(SCALED_IMAGE_HEIGHT);
	// $(".img").width(scaledWidth);
	// var left = (IMG_WIDTH-scaledWidth)/2;
	// $(".img").css("left", left);
}

function loadData(jsonFile) {
    $.getJSON(jsonFile, function(json) {
	loadPage(json);
	initPage();
    });
}

function loadPage(pageData) {
    student_name = pageData.student_name;
    banner = pageData.banner;
    for (var i = 0; i < pageData.components.length; i++) {
        var rawComponent = pageData.components[i];
        var component = new Component(rawComponent.component_type, rawComponent.component_name, rawComponent.component_path, rawComponent.component_font, rawComponent.component_desiredText);
        components[i] = component;
    }
    footer = pageData.footer;
}
function fadeInCurrentSlide() {
    var filePath = IMG_PATH + image_name[currentSlide];
    $("#slideshow").fadeOut(FADE_TIME, function(){
    $(this).attr("src", filePath).bind('onreadystatechange load', function(){
        if (this.complete) {
        $(this).fadeIn(FADE_TIME);
        $("#caption").html(image_caption[currentSlide]);
        autoScaleImage();
        }
    });
    });     
}
function processPreviousRequest() {
    currentSlide--;
    if (currentSlide < 0)
    currentSlide = image_name.length-1;
    fadeInCurrentSlide();
}

function processPlayPauseRequest() {
    if (timer === null) {
    timer = setInterval(processNextRequest, SLIDESHOW_SLEEP_TIME);
    $("#play_pause_button").attr("src", ICON_PATH + "Pause.png");
    }
    else {
    clearInterval(timer);
    timer = null;
    $("#play_pause_button").attr("src", ICON_PATH + "Play.png");
    }   
}

function processNextRequest() {
    currentSlide++;
    if (currentSlide >= image_name.length)
    currentSlide = 0;
    fadeInCurrentSlide();
}