from django.urls import path
from . import views


urlpatterns = [

    path('',views.index),
    path('login',views.login),
    path('admin_home',views.adminhome),
    path('officer_home',views.forest_officer_home),
    path("register",views.register),
    path("forestdivision",views.forestDivision),
    path("update_division/<id>",views.update_division),
    path("delete_division/<id>",views.delete_division),
    path("foreststation",views.forestStation),
    path('update_station/<id>',views.update_station),
    path('delete_station/<id>',views.delete_station),
    path('animals',views.animalManagement),
    path('update_animal/<id>',views.update_animal),
    path('delete_animal/<id>',views.delete_animal),
    path('forestofficer',views.forestOfficer),
    path('update_officer/<id>',views.update_officer),
    path('delete_officer/<id>',views.delete_officer),
    path("notifications",views.sendNotification),
    path('delete_notification/<id>',views.delete_notification),
    path('alerts',views.sendAlert),
    path('delete_alert/<id>',views.delete_alert),
    path('complaints',views.view_complaint),
    path('send_reply/<id>',views.send_reply)

]
