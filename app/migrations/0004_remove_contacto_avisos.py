# Generated by Django 3.2 on 2021-04-26 18:35

from django.db import migrations


class Migration(migrations.Migration):

    dependencies = [
        ('app', '0003_contacto'),
    ]

    operations = [
        migrations.RemoveField(
            model_name='contacto',
            name='avisos',
        ),
    ]
