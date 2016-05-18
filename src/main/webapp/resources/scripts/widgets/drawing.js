$(document).ready(function(){



    var brushWidthVar = 3;
    var imageLoader = document.getElementById('drawing-upload-input');
    imageLoader.addEventListener('change', handleImage, false);
    var canvas = new fabric.Canvas('imageCanvas');



    var pageToLoad = $("#pageID").attr("pageID");
    if(pageToLoad.length > 0) {
        var pageID = pageToLoad;

    } else {
        var pageID = "My Best Friend Gleb~Day One: The Dream of Gleb^0";

    }
    loadPage(pageID);
    //loadSVG(pageID);


    function handleImage(e){
        var reader = new FileReader();
        reader.onload = function(event){
            var img = new Image();
            img.onload = function(){
                var imgInstance = new fabric.Image(img)
                canvas.add(imgInstance);
            }
            img.src = event.target.result;
        }
        reader.readAsDataURL(e.target.files[0]);
    }

    // testing if canvas works by adding a rectengular


    // when the shape tool is clicked
    $("#shape-tool").click(function() {
        var rect = new fabric.Rect({
            left: 100,
            top: 100,
            fill: 'orange',
            width: 200,
            height: 200
        });
        canvas.add(rect);

    });

    // when the selection tool is clicked
    $("#selection-tool").click(function() {

        // turn off free drawing mode
        canvas.isDrawingMode = false;

    });

    // when the pencil tool is clicked
    $("#pencil-tool").click(function() {
        console.log("Width pencil: "+ brushWidthVar)
        // turn on free drawing mode
        canvas.isDrawingMode = true;
        canvas.freeDrawingBrush.width = brushWidthVar;
        canvas.freeDrawingBrush.color = colorVar;
    });

    // when the brush width slider is changed


    //$("#brush-width-slider").change(function() {
    //    alert(this.value);
    //    canvas.freeDrawingBrush.width = parseInt(this.value, 10) || 1;
    //    //this.previousSibling.innerHTML = this.value;
    //});


    $("#brush-width").change(function(){
      //  alert(this);
        console.log("Width: "+ this.value)
        brushWidthVar = this.value;
        canvas.freeDrawingBrush.width = brushWidthVar;
        canvas.freeDrawingBrush.color = colorVar;
    });

    function clearToolSelections() {
        $(".drawing-tool").css("background-color", "#66512c")

    }

    $("#eraser-tool").click(function(){
        canvas.remove(canvas.getActiveObject());
    });

    var fontVar = 'sans-serif';
    /* Change fonts */
    $('#font-button').click(function(){
        if($("#fonts option:selected").val() == 1){
            fontVar = 'serif';
        }
        else if($("#fonts option:selected").val() == 2){
            fontVar = 'sans-serif';
        }
    });

    var colorVar = "red";
    $('#color-button').change(function(){
        console.log(this.value);
        colorVar = this.value;
        canvas.freeDrawingBrush.color = this.value;
        textColorVar = this.value;

    });

    var textColorVar = "red";
    /*When the text tool is clicked*/
    $("#text-tool").click(function(){
        canvas.add(new fabric.IText('Double click me and type!', {
            fontFamily: fontVar,
            left: 10,
            top: 10 ,
            fill: textColorVar,
        }));
    });

    /* when the save button is pressed */
    $("#save-button").click(function(){
        // save as both SVG and Json
        // edit the JSON, use the SVG as the image!
        var json = JSON.stringify( canvas.toJSON() );
        //console.log("orginal svg: " + canvas.toSVG());
         var SVG = canvas.toSVG();

        $.post( "/get-image", {svg:SVG, json:json, pagekey:pageID})
        .done(function() {
            console.log(pageID);
            console.log("Jason is cool");
            var cID = pageID.split("^")[0];
            $(location).attr("href","../workspace/loadChapter/" + cID);
            //window.location.replace("workspace/loadChapter/" + cID); // this has to go to the chapter workspace..
            //$.get(){}
        })
        .fail(function() {
            console.log("Miuki is not cool");
        });

    });


    // this is just a testing function
    $(".drawing-tool").click(function() {
        // clear whichever tool was being used
        clearToolSelections();
        $(this).css("background-color", "white");
    });



    function loadPage(pageID) {

        $.getJSON("/load-page/" + pageID , function(data) {
        }).done(function(data) {
            console.log("loading HELLO LOOK HERE");
            console.log(data.gottenJsonImage);
            console.log(data.gottenJsonImage.jsonString);
            canvas.loadFromJSON(data.gottenJsonImage.jsonString, canvas.renderAll.bind(canvas));
        });

    }

    function loadSVG(pageID){

        $.getJSON("/load-page/" + pageID, function(data){
        }).done(function(data){
            console.log("loading page");
            console.log(data.gottenJsonImage);
            var SVG = data.gottenJsonImage.SVGString;
            //SVG =  SVG.substr(1,SVG.length-2);
            console.log(SVG);
            if(!SVG){
                console.log("image doesnt exist just make one!");
            } else {
                // internal parser error
            fabric.loadSVGFromString(SVG, function(objects, options) {
                var obj = fabric.util.groupSVGElements(objects, options);
                // objects are empty, options are also empty. i have no idea why
                // this isn't working. why?
                canvas.add(obj).renderAll();
            });
            }
        }).fail(function(data){
            console.log("NOOOOO");
        });
    }



});