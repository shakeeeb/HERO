<script language="javascript" type="text/javascript"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<!--
<script>
$(document).ready(function() {

$('.carousel[data-type="multi"] .item').each(function(){
var next = $(this).next();
if (!next.length) {
next = $(this).siblings(':first');
}
next.children(':first-child').clone().appendTo($(this));

for (var i=0;i<2;i++) {
next=next.next();
if (!next.length) {
next = $(this).siblings(':first');
}
next.children(':first-child').clone().appendTo($(this));
}
});

});
</script>
-->

<script>
    jQuery(document).ready(function() {

        jQuery('.carousel[data-type="multi"] .item').each(function(){
            var next = jQuery(this).next();
            if (!next.length) {
                next = jQuery(this).siblings(':first');
            }
            next.children(':first-child').clone().appendTo(jQuery(this));

            for (var i=0;i<2;i++) {
                next=next.next();
                if (!next.length) {
                    next = jQuery(this).siblings(':first');
                }
                next.children(':first-child').clone().appendTo($(this));
            }
        });

    });
</script>