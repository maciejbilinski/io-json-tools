function hideComparison(){
    document.getElementById("text1").classList.remove('hidden');
    document.getElementById("text2").classList.remove('hidden');
    document.getElementById("text1-diff").classList.add('hidden');
    document.getElementById("text2-diff").classList.add('hidden');
    document.getElementById("text1-diff").innerHTML = '';
    document.getElementById("text2-diff").innerHTML = '';
}

function createComparison(num, diff){
    const text = `text${num}`;
    const textDiff =  document.getElementById(`${text}-diff`);
    textDiff.innerHTML = '';
    const lines = document.getElementById(text).value.split('\n');
    for(let i=0; i<lines.length; i++){
        const cl = diff.includes(i) ? 'red' : 'green';
        textDiff.innerHTML += `<p class="${cl}">${lines[i]}</p>`
    }
}

function generateKeys() {
    const jsonText = document.getElementById("input").value.toString();
    try {
        const jsonObject = JSON.parse(jsonText);
        const keys = Object.keys(jsonObject);
        let strKeys = `<select id="json-keys" multiple class="keys-select">`;
        keys.forEach(function(i) {
            strKeys += `<option value="${i}">${i}</option>`;
        })
        strKeys += `</select>`;
        document.getElementById("keys-container").innerHTML = strKeys;
    } catch(e) {
        document.getElementById("keys-container").innerHTML = `<select multiple class="keys-select" disabled="disabled"></select>`;
    }
}

function callAPI(btn){
    const path = btn.getAttribute('operation');

    if(path == 'compare'){
        if(document.getElementById("text1").classList.contains('hidden')){
            hideComparison();
            return;
        }
    }

    btn.disabled = true;
    const xhttp = new XMLHttpRequest();


    xhttp.onload = function() {
        if(path === 'minify' || path == 'pretty' || path == 'blacklist'){
            document.getElementById("output").value = this.responseText;
        }else if(path == 'compare'){
            const output = JSON.parse(this.responseText);
            console.log(output);
            if(output.error){
                alert(output.error);
            }else{
                document.getElementById("text1").classList.add('hidden');
                document.getElementById("text2").classList.add('hidden');
                document.getElementById("text1-diff").classList.remove('hidden');
                document.getElementById("text2-diff").classList.remove('hidden');
                document.getElementById("text1-diff").addEventListener('click', hideComparison);
                document.getElementById("text2-diff").addEventListener('click', hideComparison);
                createComparison(1, output.differences);
                createComparison(2, output.differences);
            }
        }
        btn.disabled = false;
    }
    xhttp.onerror = function(e) {
        const msg = "Probably API is inaccessible. Please turn on java program.";
        if(path === 'minify' || path == 'pretty'){
            document.getElementById("output").value = msg;
        }else if(path == 'compare'){
            alert(msg);
        }
        console.log(msg);
        btn.disabled = false;
    }

    xhttp.open("POST", `http://localhost:8080/api/v1/${path}`, true);
    if(path === 'minify' || path == 'pretty'){
        xhttp.send(`{"json":${document.getElementById("input").value}}`);
    }else if(path == 'compare'){
        xhttp.send(JSON.stringify({
            text1: document.getElementById("text1").value,
            text2: document.getElementById("text2").value,
        }));
    }else if(path == 'blacklist'){
        const keys = Array.from(document.querySelectorAll('#json-keys option:checked')).map(el => el.value);
        const msgKeys = '["' + keys.join('","') + '"]';
        xhttp.send(`{"json":${document.getElementById("input").value}, "keys":${msgKeys}}`);
    }else{
        const msg = "Unimplemented frontend operation - please add it to js/action.js file!";
        document.getElementById("output").value = msg;
        console.log(msg);
        btn.disabled = false;
    }
}

window.addEventListener('load', function(){
    const actions = document.getElementsByClassName('action');
    for(let i=0; i<actions.length; i++){
        actions[i].addEventListener('click', function(e){
            e.preventDefault();
            e.stopImmediatePropagation();
            callAPI(actions[i]);
        });
    }
});