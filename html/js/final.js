// CONSTANTS
var IMG_PATH;
var ICON_PATH;
var VIDEO_PATH;
var IMG_WIDTH;
var SCALED_IMAGE_HEIGHT;
var SLIDESHOW_SLEEP_TIME;
var FADE_TIME;

// DATA
var student_name;
var components;
var page_title;
var banner;
var banner_img_path;
var footer;
var currentSlide;
var image_name;
var image_caption;
var currentPage = 0;
var contentDataFile;

// TIMER FOR PLAYING SLIDESHOW
var timer;

function Component(initType, initContent, initPath, initFont_float, initWidth,initHeight,initCaption) {
    this.component_type = initType;
    this.component_content = initContent;
    this.component_path = initPath;
    this.component_font_float = initFont_float;
    this.component_width = initWidth;
    this.component_height = initHeight;
    this.component_caption = initCaption;
}

function initEPortfolio() {
    IMG_PATH = "./img/";
    ICON_PATH = "./icons/";
    VIDEO_PATH = "./video/";
    IMG_WIDTH = 1000;
    SCALED_IMAGE_HEIGHT = 500.0;
    FADE_TIME = 1000;
    SLIDESHOW_SLEEP_TIME = 3000;
    contentDataFile = "./data/student.json";
    loadData(contentDataFile);
    timer = null;
}

function initPage() {
    $("#banner").append("<p>" + student_name + "</p>");
    $("#banner").append("<p id = banner >" + banner + "</p>");
    if (banner_name !== "") {
        $("#student_name").append("<img id = banner_img class = img src = '" + IMG_PATH + banner_img_name + "'>");
    }
    for (var i = 0; i < components.length; i++) {
        switch (components[i].component_type) {
            case 'header':
                $("#components").append("<h1>"+components[i].component_content+"</h1>");
                break;
            case 'image':
               if(components[i].component_content !== ""){
                $("#components").append("<br><img id = img"+i+" src = '" + IMG_PATH + components[i].component_content + "'>");
                $("#img"+i).append("<p>"+components[i].component_caption+"</p>");
                $("#img"+i).width(components[i].component_width);
                $("#img"+i).height(components[i].component_height);
                $("#img"+i).css("float",components[i].component_font_float);}
                break;
            case 'video':
                if(components[i].component_content !== ""){
                $("#components").append("<br><video id = video"+i+"> <source src = '" + VIDEO_PATH + components[i].component_content + "'></video>");
                $("#video"+i).append("<p>"+components[i].component_caption+"</p>");
                $("#video"+i).width(components[i].component_width);
                $("#img"+i).height(components[i].component_height);}
                break;
            case 'list':
                var component_list = components[i].component_content.split(",");
                $("#components").append("<br><ul class='list'>");
                for (var j = 0; j < component_list.length; j++) {
                    $("#components").append("<li>" + component_list[j] + "</li>");
                }
                $("#components").append("</ul>");
                break;
            case 'paragraph':
                $("#components").append("<br><div id = paragraph"+i+">" + components[i].component_content + "</div>");
                $("#paragraph"+i).css("font-family",components[i].component_font_float);
                break;
            case 'slideshow':
                image_name = components[i].component_content.split(",");
                image_caption = components[i].component_caption.split(",");
                if (image_name.length > 0) {
                    currentSlide = 0;
                    $("#components").append("<img id = slideshow class = img src = '" + IMG_PATH + image_name[currentSlide] + "'>");
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
    $("#footer").html(footer);
}

function loadData(jsonFile,i) {
    $.getJSON(jsonFile, function (json) {
        components = new Array();
        page_title = new Array();
        loadEPortfolio(json);
        for (var i = 0; i < page_title.length; i++) {
            $("#nav_bar ul").append("<li><a href='' onclick = loadData("+json+","+i+") ><span>" + page_title[i] + "</span></a></li>");
        }
        loadPage(json);
        initPage();
    });
}

function loadEPortfolio(ePortfolioData) {
    student_name = ePortfolioData.student_name;
    for (var i = 0; i < ePortfolioData.pages.length; i++) {
        page_title.push(ePortfolioData.pages[i].page_title);
    }
}
function loadPage(pageData) {
    banner = pageData.banner;
    $("#whole").css("font-family",pageData.page_font);
    $("#layout").href = pageData.layout;
    $("#color").href = pageData.color;
    banner_name = pageData.banner_file_name;
    banner_path = pageData.banner_file_path;
    for (var i = 0; i < pageData.components.length; i++) {
        var rawComponent = pageData.components[i];
        var component = new Component(rawComponent.component_type, rawComponent.component_content, rawComponent.component_path, rawComponent.component_font_float, rawComponent.component_width, rawComponent.component_height, rawComponent.component_caption);
        components[i] = component;
    }
    footer = pageData.footer;
}

/*-------------slideshow controls-------------*/
function fadeInCurrentSlide() {
    var filePath = IMG_PATH + image_name[currentSlide];
    $("#slideshow").fadeOut(FADE_TIME, function () {
        $(this).attr("src", filePath).bind('onreadystatechange load', function () {
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
        currentSlide = image_name.length - 1;
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