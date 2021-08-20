from django.shortcuts import render, redirect, get_object_or_404
from .models import Producto, Cliente
from .forms import ContactoForm, ProductoForm
from django.views.generic import View
from django.http import HttpResponse
from .utils import render_to_pdf
from django.contrib import messages
import folium

# Create your views here.

def home(request):
    productos = Producto.objects.all()
    data = {
        'productos': productos
    }
    return render(request, 'app/home.html', data)

def contacto(request):
    data = {
        'form': ContactoForm()
    }
    
    if request.method == 'POST':
        formulario = ContactoForm(data=request.POST)
        if formulario.is_valid():
            formulario.save()
            data["mensaje"] = "Mensaje de contacto enviado!"
        else:
            data["form"] = formulario

    return render(request, 'app/contacto.html', data)

def galeria(request):
    return render(request, 'app/galeria.html')

def faq(request):
    return render(request, 'app/faq.html')

def horario(request):
    return render(request, 'app/horario.html')

def ubicacion(request):
    #create map object
    m = folium.Map(location=[-33.45694, -70.64827], zoom_start=10)
    folium.CircleMarker([-33.45694, -70.64827], fill=True,
    fill_color='#3186cc',radius=5,
    color='blue',tooltip = 'Click for more',
    popup="Almacén Los Yuyitos").add_to(m)
 
        # GET HTML REPRESENTATION
    m = m._repr_html_()
    context = {
        'm': m,
    }
    return render(request, 'app/ubicacion.html', context)

def agregar_producto(request):
    data = {
        'form': ProductoForm()
    }

    if request.method == 'POST':
        formulario = ProductoForm(data=request.POST, files=request.FILES)
        if formulario.is_valid():
            formulario.save()
            messages.success(request, "Agregado correctamente")
        else:
            data["form"] = formulario
        
    return render(request, 'app/producto/agregar.html', data)

def listar_productos(request):
    productos = Producto.objects.all()

    data = {
        'productos': productos
    }
    return render(request, 'app/producto/listar.html', data)

def listar_clientes(request):
    clientes = Cliente.objects.all()
    context_object_name = 'clientes'
    data = {
        'clientes': clientes
    }
    return render(request, 'app/clientes.html', data)

class ListClientesPdf(View):
    def get(self, request, *args, **kwargs):
        clientes = Cliente.objects.all()
        data = {
            'clientes': clientes
        }
        pdf = render_to_pdf('app/listar.html', data)
        return HttpResponse(pdf, content_type = 'application/pdf')

def modificar_producto(request, id):
    producto = get_object_or_404(Producto, id = id)

    data = {
        'form': ProductoForm(instance=producto)
    }

    if request.method == 'POST':
        formulario = ProductoForm(data=request.POST, instance= producto, files=request.FILES)
        if formulario.is_valid():
            formulario.save()
            messages.success(request, "Modificado Correctamente")
            return redirect(to= "listar_productos")
        data["form"] = formulario
    return render(request, 'app/producto/modificar.html', data)

def eliminar_producto(request, id):
    producto = get_object_or_404(Producto, id = id)
    producto.delete()
    messages.success(request, "Eliminado Correctamente")
    return redirect(to='listar_productos')