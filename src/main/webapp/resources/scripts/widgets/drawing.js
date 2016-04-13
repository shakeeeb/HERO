$(document).ready(function(){

    var brushWidthVar = 3;

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
        //alert(this);
        console.log("Width: "+ this.value)
        brushWidthVar = this.value;
        canvas.freeDrawingBrush.width = brushWidthVar;
        canvas.freeDrawingBrush.color = colorVar;
    });

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

    var colorVar = "red";
    $('#color-button').change(function(){
        console.log(this.value);
        colorVar = this.value;
        canvas.freeDrawingBrush.color = this.value;

    });


    /*When the text tool is clicked*/
    $("#text-tool").click(function(){
        canvas.add(new fabric.IText('Double click me and type!', {
            fontFamily: fontVar,
            left: 10,
            top: 10 ,
        }));
    });


    // this is just a testing function
    $(".drawing-tool").click(function() {
        // clear whichever tool was being used
        clearToolSelections();
        $(this).css("background-color", "white");
    });
});