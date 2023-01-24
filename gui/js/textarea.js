function intelligentTextarea(e){
    if (e.key == 'Tab') {
        e.preventDefault();
        var start = this.selectionStart;
        var end = this.selectionEnd;

        // set textarea value to: text before caret + tab + text after caret
        this.value = this.value.substring(0, start) + "    " + this.value.substring(end);

        // put caret at right position again
        this.selectionStart = this.selectionEnd = start + 4;
    }else if(e.key == '{'){
        e.preventDefault();
        var start = this.selectionStart;
        var end = this.selectionEnd;

        // set textarea value to: text before caret + tab + text after caret
        this.value = this.value.substring(0, start) + "{\n\n}" + this.value.substring(end);

        // put caret at right position again
        this.selectionStart = this.selectionEnd = start + 2;
    }else if(e.key == '['){
            e.preventDefault();
            var start = this.selectionStart;
            var end = this.selectionEnd;

            // set textarea value to: text before caret + tab + text after caret
            this.value = this.value.substring(0, start) + "[\n\n]" + this.value.substring(end);

            // put caret at right position again
            this.selectionStart = this.selectionEnd = start + 2;
        }
}

window.addEventListener('load', function(){
    const actions = document.getElementsByClassName('action');
    for(let i=0; i<actions.length; i++){
        actions[i].addEventListener('click', function(){
            callAPI(actions[i]);
        });
    }
    document.querySelectorAll('.textarea.input').forEach(function(t){
        t.addEventListener('keydown', intelligentTextarea);
    });
});