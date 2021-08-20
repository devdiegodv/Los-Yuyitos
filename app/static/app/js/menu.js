


let menu = document.getElementById("menu")
let header = document.getElementById("header")
let nav = document.getElementById("nav")
menu.addEventListener("click", function() {
    
    if(header.style.height == "60px" || header.offsetHeight == 60) {
        header.style.height = 60 + nav.offsetHeight + "px"
    } else {
        header.style.height = "60px"
    }
    
})

window.addEventListener("resize", function() {

    let anchoVentana = document.documentElement.clientWidth

    if(anchoVentana > 480) {
        header.style = "none"
    }

})

$(document).ready(function(){
 
    if(window.innerWidth < 768){
        $('.btn').addClass('btn-sm');
    }
    // Medida por defecto (Sin ningún nombre de clase)
    else if(window.innerWidth < 900){
        $('.btn').removeClass('btn-sm');
    }
 
    // Si el ancho del navegador es menor a 1200 px le asigno la clase 'btn-lg' 
    else if(window.innerWidth < 1200){
        $('.btn').addClass('btn-lg');
    }
 
});
