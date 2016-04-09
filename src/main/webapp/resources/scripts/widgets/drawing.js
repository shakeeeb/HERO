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


    //$("#brush-width-slider").change(function() {
    //    alert(this.value);
    //    canvas.freeDrawingBrush.width = parseInt(this.value, 10) || 1;
    //    //this.previousSibling.innerHTML = this.value;
    //});

    $("#brush-width-slider").on("change", function(){
        alert(this.value);
        //console.log(this.value)
    });

    function clearToolSelections() {
        $(".drawing-tool").css("background-color", "#66512c");
    }

    // this is just a testing function
    $(".drawing-tool").click(function() {
        // clear whichever tool was being used
        clearToolSelections();
        $(this).css("background-color", "white");
    });
});