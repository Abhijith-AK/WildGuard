# Generated by Django 5.0.1 on 2024-03-19 11:45

from django.db import migrations, models


class Migration(migrations.Migration):
    dependencies = [
        ("app", "0006_remove_chat_login_chat_usertype"),
    ]

    operations = [
        migrations.AlterField(
            model_name="chat",
            name="date",
            field=models.CharField(default="SOME STRING", max_length=100),
        ),
    ]