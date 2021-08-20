from django.db import models
# Create your models here.

class Marca(models.Model):
    nombre = models.CharField(max_length = 50)

    def __str__(self):
        return self.nombre

class Producto(models.Model):
    nombre = models.CharField(max_length = 50)
    precio = models.IntegerField()
    descripcion = models.TextField()
    marca = models.ForeignKey(Marca, on_delete = models.PROTECT)
    fecha_elaboracion = models.DateField()
    imagen = models.ImageField(upload_to = "productos", null = True)

    def __str__(self):
        return self.nombre

#cambia la imagen de la BD al actualizar/borrar producto
#from django.db.models.signals import post_delete
#from django.dispatch.dispatcher import receiver

#@receiver(post_delete , sender=Producto)
#def mymodel_delete(sender, instance, **kwargs):
#       instance.imagen.delete(False) #nombre del archivo

opciones_consultas = [
    [0, "Consulta"],
    [1, "Reclamo"],
    [2, "Sugerencia"],
    [3, "Felicitaciones"]
]

class Contacto(models.Model):
    nombre = models.CharField(max_length = 36)
    correo = models.EmailField()
    tipo_consulta = models.IntegerField(choices = opciones_consultas)
    mensaje = models.TextField()

    def __str__(self):
        return self.nombre

class Cliente(models.Model):
    idcliente = models.AutoField(db_column='idCliente', primary_key=True)  # Field name made lowercase.
    fecharegistro = models.DateField(db_column='fechaRegistro')  # Field name made lowercase.
    nombre = models.CharField(max_length=15)
    apaterno = models.CharField(max_length=15, blank=True, null=True)
    amaterno = models.CharField(max_length=15, blank=True, null=True)
    rut = models.IntegerField()
    dv = models.CharField(max_length=1)
    direccion = models.CharField(max_length=30)
    deuda = models.CharField(max_length=2)
    fiado_idfiado = models.IntegerField(db_column='fiado_idFiado')  # Field name made lowercase.

    class Meta:
        managed = False
        db_table = 'cliente'
        unique_together = (('idcliente', 'fiado_idfiado'),)

class Boleta(models.Model):
    numboleta = models.AutoField(db_column='numBoleta', primary_key=True)  # Field name made lowercase.
    fecha = models.DateField()
    total = models.IntegerField()
    deuda = models.IntegerField(blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'boleta'


class CodBar(models.Model):
    idempresa = models.IntegerField(db_column='idEmpresa', primary_key=True)  # Field name made lowercase.
    producto_familia = models.IntegerField()
    producto_fechavenc = models.IntegerField(db_column='producto_fechaVenc')  # Field name made lowercase.
    producto_tipo = models.IntegerField()

    class Meta:
        managed = False
        db_table = 'cod_bar'
        unique_together = (('idempresa', 'producto_familia', 'producto_fechavenc', 'producto_tipo'),)


class Detalleboleta(models.Model):
    iddetalle = models.AutoField(db_column='idDetalle', primary_key=True)  # Field name made lowercase.
    numboleta = models.ForeignKey(Boleta, models.DO_NOTHING, db_column='numboleta')
    producto = models.ForeignKey('Producto', models.DO_NOTHING, db_column='producto')
    cantidad = models.IntegerField(blank=True, null=True)
    precio = models.IntegerField(blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'detalleboleta'
        unique_together = (('iddetalle', 'producto', 'numboleta'),)


class Detallefactura(models.Model):
    iddetalle = models.AutoField(db_column='idDetalle', primary_key=True)  # Field name made lowercase.
    numfactura = models.ForeignKey('Factura', models.DO_NOTHING, db_column='numFactura')  # Field name made lowercase.
    producto = models.ForeignKey('Producto', models.DO_NOTHING, db_column='producto')
    cantidad = models.IntegerField(blank=True, null=True)
    precio = models.IntegerField(blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'detallefactura'
        unique_together = (('iddetalle', 'producto', 'numfactura'),)


class Discrepancia(models.Model):
    iddiscrepancia = models.AutoField(db_column='idDiscrepancia', primary_key=True)  # Field name made lowercase.
    numfactura = models.ForeignKey('Factura', models.DO_NOTHING, db_column='numfactura')
    cantidad = models.IntegerField()
    precio = models.IntegerField()
    informacionadicional = models.CharField(db_column='informacionAdicional', max_length=60, blank=True, null=True)  # Field name made lowercase.

    class Meta:
        managed = False
        db_table = 'discrepancia'
        unique_together = (('iddiscrepancia', 'numfactura'),)

class Empleado(models.Model):
    idempleado = models.AutoField(db_column='idEmpleado', primary_key=True)  # Field name made lowercase.
    nombre = models.CharField(max_length=15)
    apaterno = models.CharField(max_length=15)
    amaterno = models.CharField(max_length=15, blank=True, null=True)
    rut = models.IntegerField()
    dv = models.CharField(max_length=1)
    direccion = models.CharField(max_length=30)
    telefono = models.IntegerField()
    fechacontrato = models.DateField(db_column='fechaContrato')  # Field name made lowercase.
    fechatermino = models.DateField(db_column='fechaTermino')  # Field name made lowercase.
    cargo = models.CharField(max_length=20)
    sueldo = models.IntegerField()

    class Meta:
        managed = False
        db_table = 'empleado'


class Empresa(models.Model):
    idempresa = models.AutoField(db_column='idEmpresa', primary_key=True)  # Field name made lowercase.
    nombre = models.CharField(max_length=15)
    rut = models.IntegerField()
    dv = models.CharField(max_length=1)
    rubro = models.CharField(max_length=15, blank=True, null=True)
    direccion = models.CharField(max_length=30)
    telefono = models.IntegerField()
    email = models.CharField(max_length=50)

    class Meta:
        managed = False
        db_table = 'empresa'

    def __str__(self):
        return self.nombre


class Factura(models.Model):
    numfactura = models.AutoField(db_column='numFactura', primary_key=True)  # Field name made lowercase.
    fecha = models.DateField()
    costo = models.IntegerField()
    iva = models.IntegerField()
    total = models.IntegerField()

    class Meta:
        managed = False
        db_table = 'factura'


class Familia(models.Model):
    idfamilia = models.IntegerField(db_column='idFamilia', primary_key=True)  # Field name made lowercase.
    decripcion = models.CharField(max_length=45, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'familia'

    def __str__(self):
        return self.decripcion


class Fiado(models.Model):
    idfiado = models.AutoField(db_column='idFiado', primary_key=True)  # Field name made lowercase.
    monto = models.CharField(max_length=45, blank=True, null=True)
    plazo = models.CharField(max_length=45, blank=True, null=True)
    idventa = models.ForeignKey('Venta', models.DO_NOTHING, db_column='idVenta')  # Field name made lowercase.

    class Meta:
        managed = False
        db_table = 'fiado'
        unique_together = (('idfiado', 'idventa'),)


class Inventario(models.Model):
    fechainventario = models.DateField(db_column='fechaInventario', primary_key=True)  # Field name made lowercase.
    idempleado = models.ForeignKey(Empleado, models.DO_NOTHING, db_column='idEmpleado')  # Field name made lowercase.
    producto_codproducto = models.ForeignKey('Producto', models.DO_NOTHING, db_column='producto_codProducto')  # Field name made lowercase.
    stockcritico = models.IntegerField(db_column='stockCritico')  # Field name made lowercase.
    stock = models.IntegerField()

    class Meta:
        managed = False
        db_table = 'inventario'
        unique_together = (('fechainventario', 'idempleado', 'producto_codproducto'),)


class Pedido(models.Model):
    idpedido = models.AutoField(db_column='idPedido', primary_key=True)  # Field name made lowercase.
    idempleado = models.ForeignKey(Empleado, models.DO_NOTHING, db_column='idEmpleado')  # Field name made lowercase.
    idrepartidor = models.ForeignKey('Repartidor', models.DO_NOTHING, db_column='idRepartidor')  # Field name made lowercase.
    numfactura = models.ForeignKey(Factura, models.DO_NOTHING, db_column='numFactura')  # Field name made lowercase.

    class Meta:
        managed = False
        db_table = 'pedido'
        unique_together = (('idpedido', 'idempleado', 'idrepartidor', 'numfactura'),)


#class Producto(models.Model):
#    codproducto = models.AutoField(db_column='codProducto', primary_key=True)  # Field name made lowercase.
#    descripcion = models.CharField(max_length=45)
#    familia = models.ForeignKey(Familia, models.DO_NOTHING, db_column='familia')
#    tipo = models.ForeignKey('Tipo', models.DO_NOTHING, db_column='tipo')
#    fechaelab = models.DateField(db_column='fechaElab')  # Field name made lowercase.
#    fechavenc = models.DateField(db_column='fechaVenc')  # Field name made lowercase.
#    idempresa = models.ForeignKey(Empresa, models.DO_NOTHING, db_column='idempresa')
#    precioprodunitario = models.IntegerField(blank=True, null=True)
#    imagen = models.ImageField(upload_to = "productos", null = True)


#    class Meta:
#        managed = False
#        db_table = 'producto'
#        unique_together = (('codproducto', 'familia', 'tipo', 'fechavenc', 'idempresa'),)

#    def __str__(self):
#        return self.descripcion


class Repartidor(models.Model):
    idrepartidor = models.AutoField(db_column='idRepartidor', primary_key=True)  # Field name made lowercase.
    nombre = models.CharField(max_length=15)
    apaterno = models.CharField(max_length=15)
    amaterno = models.CharField(max_length=15, blank=True, null=True)
    rut = models.IntegerField()
    dv = models.CharField(max_length=1)
    idempresa = models.ForeignKey(Empresa, models.DO_NOTHING, db_column='idEmpresa')  # Field name made lowercase.

    class Meta:
        managed = False
        db_table = 'repartidor'
        unique_together = (('idrepartidor', 'idempresa'),)


class Tipo(models.Model):
    idtipo = models.IntegerField(db_column='idTipo', primary_key=True)  # Field name made lowercase.
    descripcion = models.CharField(max_length=45)

    class Meta:
        managed = False
        db_table = 'tipo'

    def __str__(self):
        return self.descripcion


class Usuarios(models.Model):
    idusuario = models.AutoField(primary_key=True)
    correo = models.CharField(max_length=50)
    pass_field = models.CharField(db_column='pass', max_length=50)  # Field renamed because it was a Python reserved word.

    class Meta:
        managed = False
        db_table = 'usuarios'


class Venta(models.Model):
    idventa = models.AutoField(db_column='idVenta', primary_key=True)  # Field name made lowercase.
    idempleado = models.ForeignKey(Empleado, models.DO_NOTHING, db_column='idEmpleado')  # Field name made lowercase.
    numboleta = models.ForeignKey(Boleta, models.DO_NOTHING, db_column='numBoleta')  # Field name made lowercase.

    class Meta:
        managed = False
        db_table = 'venta'
        unique_together = (('idventa', 'idempleado', 'numboleta'),)