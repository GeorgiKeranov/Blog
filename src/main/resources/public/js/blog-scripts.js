//Script for auto-sizing textarea.
$("textarea").keyup(function () {
   $(this).css({'height' : 'auto'}).height(this.scrollHeight);
});

//Script for showing image before upload it to server.
$("#picture").change(function () {

    if(this.files && this.files[0]){

        var reader = new FileReader();
        reader.readAsDataURL(this.files[0]);
        reader.onload = function (image) {
            $("#image").attr('src', image.target.result);
            $("#profilePic").attr('src', image.target.result);
        }
    }
    else {
        $("#image").removeAttr("src");
        $("#profilePic").removeAttr("src");
    }

});

//Confirmation window if the user wrong clicked delete.
$("#delete").click(function () {
   var answer = confirm('Are you sure you want to delete it ?');
   if (answer){
       return true;
   }
   else return false;
});

$(".reply").click(function () {

    var replyDiv;

    // Checking who element is!
    var elId = this.id;
    if(elId == "replyOnReply")
        replyDiv = $(this).parent().parent().parent().parent().parent().parent().children().children('.reply-comment');
    else
        replyDiv = $(this).parent().parent().parent().parent().children().children('.reply-comment');

    var showElement = false;
    if(replyDiv.css('display') == "none") showElement = true;

    $(".reply-comment").hide();

    if(showElement) replyDiv.show();
});