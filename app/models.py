from django.db import models

# Create your models here.

class Login(models.Model):
    username = models.CharField(max_length=20,default='SOME STRING')
    password = models.CharField(max_length=20,default='SOME STRING') 
    usertype = models.CharField(max_length=20,default='SOME STRING') 

class Forest_Officer(models.Model):
    LOGIN = models.ForeignKey(Login,on_delete=models.CASCADE)
    fname = models.CharField(max_length=20,default='SOME STRING')
    lname = models.CharField(max_length=20,default='SOME STRING')
    place = models.CharField(max_length=20,default='SOME STRING')
    phone = models.CharField(max_length=20,default='SOME STRING')
    email = models.CharField(max_length=30,default='SOME STRING')
    designation = models.CharField(max_length=20,default='SOME STRING')

class Forest_Division(models.Model):
    division_name = models.CharField(max_length=20,default='SOME STRING')

class Forest_Station(models.Model):
        FOREST_DIVISION = models.ForeignKey(Forest_Division,on_delete=models.CASCADE)
        station_name = models.CharField(max_length=20,default='SOME STRING')
        place = models.CharField(max_length=20,default='SOME STRING') 
        contact_number = models.CharField(max_length=20,default='SOME STRING')

class User(models.Model):
    LOGIN = models.ForeignKey(Login,on_delete=models.CASCADE)
    FOREST_STATION = models.ForeignKey(Forest_Station,on_delete=models.CASCADE)
    fname = models.CharField(max_length=20,default='SOME STRING') 
    lname = models.CharField(max_length=20,default='SOME STRING') 
    place = models.CharField(max_length=20,default='SOME STRING') 
    phone = models.CharField(max_length=20,default='SOME STRING') 
    email = models.CharField(max_length=30,default='SOME STRING') 

class Animal(models.Model):
    FOREST_DIVISION = models.ForeignKey(Forest_Division,on_delete=models.CASCADE)
    animal_name = models.CharField(max_length=20,default='SOME STRING') 
    animal_image = models.ImageField(upload_to='static/animal')
    animal_description = models.CharField(max_length=100,default='SOME STRING') 
    safety_tips = models.CharField(max_length=50,default='SOME STRING') 
    

class Preserved_Animal(models.Model):
    ANIMAL = models.ForeignKey(Animal,on_delete=models.CASCADE)


class Complaints(models.Model):
    USER = models.ForeignKey(User,on_delete=models.CASCADE)
    title = models.CharField(max_length=20,default='SOME STRING') 
    description = models.CharField(max_length=100,default='SOME STRING') 
    reply = models.CharField(max_length=100,default='SOME STRING') 
    date = models.CharField(max_length=20,default='SOME STRING')     

class Notification(models.Model):
    title = models.CharField(max_length=20,default='SOME STRING') 
    description = models.CharField(max_length=1000,default='SOME STRING') 
    date = models.CharField(max_length=20,default='SOME STRING')     
      
class Allocate(models.Model):
    FOREST_STATION = models.ForeignKey(Forest_Station,on_delete=models.CASCADE)
    FOREST_OFFICER = models.ForeignKey(Forest_Officer,on_delete=models.CASCADE)
    status = models.CharField(max_length=20,default='SOME STRING')

class Alert(models.Model):
    FOREST_OFFICER = models.ForeignKey(Forest_Officer,on_delete=models.CASCADE)
    description = models.CharField(max_length=100,default='SOME STRING') 
    date = models.CharField(max_length=20,default='SOME STRING')         

class Chat(models.Model):
    FOREST_OFFICER = models.ForeignKey(Forest_Officer,on_delete=models.CASCADE)
    USER = models.ForeignKey(User,on_delete=models.CASCADE)
    message = models.CharField(max_length=100,default='SOME STRING') 
    date = models.CharField(max_length=100,default='SOME STRING') 
    usertype = models.CharField(max_length=20)
   
class Emergency_Message(models.Model):
    USER = models.ForeignKey(User,on_delete=models.CASCADE)
    message_image = models.ImageField(upload_to='static/animal')
    description = models.CharField(max_length=100,default='SOME STRING') 


   
