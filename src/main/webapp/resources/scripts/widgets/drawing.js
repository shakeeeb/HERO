/**
 * Drawing application for HERO
 * Created by Terrell Mack on 3/30/16.
 */

$(document).ready(function(){

    var imageLoader = document.getElementById('drawing-upload-input');
    imageLoader.addEventListener('change', handleImage, false);
    var canvas = new fabric.Canvas('imageCanvas');

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
    var rect = new fabric.Rect({
        left: 100,
        top: 100,
        fill: 'orange',
        width: 200,
        height: 200
    });
    canvas.add(rect);


    // when the selection tool is clicked
    $("#selection-tool").click(function() {

        // turn off free drawing mode
        canvas.isDrawingMode = false;

    });

    // when the pencil tool is clicked
    $("#pencil-tool").click(function() {

        // turn on free drawing mode
        canvas.isDrawingMode = true;
        canvas.freeDrawingBrush.width = 3;
        canvas.freeDrawingBrush.color = "black";
    });

    // when the brush width slider is changed


    $("#brush-width").change(function() {
        console.log( $(this).val() );
        canvas.freeDrawingBrush.width = parseInt(this.value, 10) || 1;
        //this.previousSibling.innerHTML = this.value;
    });

    //$("#brush-width-slider").on("change", function(){
    //    console.log(this.value)
    //});


    //$('drawing-mode-selector').onchange = function() {
    //    if (this.value === 'hline') {
    //        canvas.freeDrawingBrush = vLinePatternBrush;
    //    }
    //    else if (this.value === 'vline') {
    //        canvas.freeDrawingBrush = hLinePatternBrush;
    //    }
    //    else if (this.value === 'square') {
    //        canvas.freeDrawingBrush = squarePatternBrush;
    //    }
    //    else if (this.value === 'diamond') {
    //        canvas.freeDrawingBrush = diamondPatternBrush;
    //    }
    //    else if (this.value === 'texture') {
    //        canvas.freeDrawingBrush = texturePatternBrush;
    //    }
    //    else {
    //        canvas.freeDrawingBrush = new fabric[this.value + 'Brush'](canvas);
    //    }
    //
    //    if (canvas.freeDrawingBrush) {
    //        canvas.freeDrawingBrush.color = drawingColorEl.value;
    //        canvas.freeDrawingBrush.width = parseInt(drawingLineWidthEl.value, 10) || 1;
    //        canvas.freeDrawingBrush.shadowBlur = parseInt(drawingShadowWidth.value, 10) || 0;
    //    }
    //};

    function clearToolSelections() {
        $(".drawing-tool").css("background-color", "#66512c")

    }


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


    /*When the text tool is clicked*/
    $("#text-tool").click(function(){
        canvas.add(new fabric.IText('Double click me and type!', {
            fontFamily: fontVar,
            left: 10,
            top: 10 ,
        }));
    });


    $("#eraser-tool").click(function(){
        canvas.remove(canvas.getActiveObject());
    });


    // this is just a testing function
    $(".drawing-tool").click(function() {
        // clear whichever tool was being used
        clearToolSelections();
        $(this).css("background-color", "white");
    });
});