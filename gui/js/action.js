function callAPI(btn){
    btn.disabled = true;
    const xhttp = new XMLHttpRequest();
    xhttp.onload = function() {
        document.getElementById("output").value = this.responseText;
        btn.disabled = false;
    }
    xhttp.onerror = function(e) {
        document.getElementById("output").value = "Probably API is inaccessible. Please turn on java program.";
        btn.disabled = false;
    }
    const path = btn.getAttribute('operation');
    const data = document.getElementById("input").value;

    xhttp.open("POST", `http://localhost:8080/api/v1/${path}`, true);
    if(path === 'minify' || path == 'pretty'){
        xhttp.send(`{"json":${data}}`);
    }else{
        document.getElementById("output").value = "Unimplemented frontend operation - please add it to js/action.js file!";
        btn.disabled = false;
    }
}

window.addEventListener('load', function(){
    const actions = document.getElementsByClassName('action');
    for(let i=0; i<actions.length; i++){
        actions[i].addEventListener('click', function(){
            callAPI(actions[i]);
        });
    }
});