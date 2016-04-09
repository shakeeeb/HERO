/**
 * Drawing application for HERO
 * Created by Terrell Mack on 3/30/16.
 */

$(document).ready(function(){

    var canvas;

    // initialize canvas
    canvas = new fabric.Canvas('drawing-canvas');

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


    //$("#brush-width-slider").change(function() {
    //    alert(this.value);
    //    canvas.freeDrawingBrush.width = parseInt(this.value, 10) || 1;
    //    //this.previousSibling.innerHTML = this.value;
    //});

    $("#brush-width-slider").on("change", function(){
        console.log(this.value)
    });


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

    // this is just a testing function
    $(".drawing-tool").click(function() {
        // clear whichever tool was being used
        clearToolSelections();
        $(this).css("background-color", "white");
    });
});