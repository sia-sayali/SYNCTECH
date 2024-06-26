let input = document.getElementById('inbox');
let buttons = document.querySelectorAll('button');

let num = "";
let arr = Array.from(buttons);
arr.forEach(button => {
    button.addEventListener('click', (e) =>{
        if(e.target.innerHTML == '=') {
            try {
                num = eval(num);
                input.value = num;
            }
            catch(error) {
                input.value = "ERROR";
            }
        }

        else if(e.target.innerHTML == 'C') {
            num = "";
            input.value = num;
        }

        else if(e.target.innerHTML == '⌫') {
            num = num.substring(0, num.length-1);
            input.value = num;
        }

        else {
            num += e.target.innerHTML;
            input.value = num;
        }
    })
})