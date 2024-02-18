# Generated by Django 5.0.1 on 2024-01-29 17:07

import django.db.models.deletion
from django.db import migrations, models


class Migration(migrations.Migration):
    dependencies = [
        ("app", "0001_initial"),
    ]

    operations = [
        migrations.AddField(
            model_name="login",
            name="password",
            field=models.CharField(default="SOME STRING", max_length=20),
        ),
        migrations.AddField(
            model_name="login",
            name="username",
            field=models.CharField(default="SOME STRING", max_length=20),
        ),
        migrations.AddField(
            model_name="login",
            name="usertype",
            field=models.CharField(default="SOME STRING", max_length=20),
        ),
        migrations.CreateModel(
            name="Forest_Officer",
            fields=[
                (
                    "id",
                    models.BigAutoField(
                        auto_created=True,
                        primary_key=True,
                        serialize=False,
                        verbose_name="ID",
                    ),
                ),
                ("fname", models.CharField(default="SOME STRING", max_length=20)),
                ("lname", models.CharField(default="SOME STRING", max_length=20)),
                ("place", models.CharField(default="SOME STRING", max_length=20)),
                ("phone", models.CharField(default="SOME STRING", max_length=20)),
                ("email", models.CharField(default="SOME STRING", max_length=20)),
                ("designation", models.CharField(default="SOME STRING", max_length=20)),
                (
                    "LOGIN",
                    models.ForeignKey(
                        on_delete=django.db.models.deletion.CASCADE, to="app.login"
                    ),
                ),
            ],
        ),
    ]
