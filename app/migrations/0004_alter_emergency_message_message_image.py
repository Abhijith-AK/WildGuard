# Generated by Django 5.0.1 on 2024-03-18 08:07

from django.db import migrations, models


class Migration(migrations.Migration):
    dependencies = [
        ("app", "0003_alter_alert_description_alter_animal_safety_tips_and_more"),
    ]

    operations = [
        migrations.AlterField(
            model_name="emergency_message",
            name="message_image",
            field=models.ImageField(upload_to="static/animal"),
        ),
    ]
