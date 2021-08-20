from django import forms
from .models import Contacto, Producto
from django import forms 
#from .models import Location  

class ContactoForm(forms.ModelForm):
    class Meta:
        model = Contacto
        fields = ["nombre", "correo", "tipo_consulta", "mensaje"] 
        #fields = '__all__'

class ProductoForm(forms.ModelForm):
    class Meta:
        model = Producto
        fields = '__all__'

        widgets = {
            "fecha_elaboracion": forms.SelectDateWidget()
        }