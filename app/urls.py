from django.urls import path
from . import views
from .views import home, contacto, galeria, agregar_producto, listar_productos, modificar_producto, eliminar_producto,listar_clientes,faq,horario,ubicacion

urlpatterns = [
    path('', home, name = "home"),
    path('contacto/', contacto, name = "contacto"),
    path('galeria/', galeria, name = "galeria"),
    path('agregar-producto/', agregar_producto, name = "agregar_producto"),
    path('listar-producto/', listar_productos, name = "listar_productos"),
    path('modificar-producto/<id>', modificar_producto, name = "modificar_producto"),
    path('eliminar-producto/<id>/', eliminar_producto, name = "eliminar_producto"),
    path('listar-clientes/', listar_clientes, name = "listar_clientes"),
    path('faq/', faq, name = "faq"),
    path('horario/', horario, name = "horario"),
    path('ubicacion/', ubicacion, name = "ubicacion"),
    path('listar_clientes/',views.ListClientesPdf.as_view(),name='clientes_all')
]