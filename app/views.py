from django.http import HttpResponse
from django.shortcuts import render,redirect
from  . models import *

# Create your views here.

def index(request):
    return render(request,'index.html')
    return render(request,"publicindex.html")

def adminhome(request):
    return render(request,'admin/admin_home.html')


def forest_officer_home(request):
    return render(request,'forest_officer/officer_home.html')


def login(request):
    if request.method == 'POST':
        username = request.POST.get('username')
        password = request.POST.get('password')

        try:
            login_obj = Login.objects.get(username=username, password=password)
        except Login.DoesNotExist:
            # If username and password do not match, or user does not exist
            error_message = "Invalid username or password."
            return render(request, 'login.html', {'error_message': error_message})

        request.session['lid'] = login_obj.pk

        if login_obj.usertype == "admin":
            return redirect(adminhome)
        elif login_obj.usertype == "forest_officer":
            try:
                forest_officer_obj = Forest_Officer.objects.get(LOGIN_id=login_obj.pk)
                request.session['fid'] = forest_officer_obj.pk
                return redirect(emmergency_message)
            except Forest_Officer.DoesNotExist:
                error_message = "Forest officer record not found."
                return render(request, 'login.html', {'error_message': error_message})

    return render(request, "login.html")


def register(request):
    return render(request,"register.html")

def forestDivision(request):
    q2 = Forest_Division.objects.all()
    if 'submit' in request.POST:
        dname = request.POST['dname']
        q1 = Forest_Division(division_name=dname)
        q1.save()
        return HttpResponse("<script>alert('added....');window.location='/forestdivision'</script>")  

    return render(request,"admin/forest_division.html",{'q2':q2})

def update_division(request,id):
    q3 = Forest_Division.objects.get(id=id)
    if 'update' in request.POST:
        dname = request.POST['dname']
        q3.division_name=dname
        q3.save()
        return HttpResponse("<script>alert('updated....');window.location='/forestdivision'</script>")  
    return render(request,"admin/forest_division.html",{'q3':q3})

def delete_division(request,id):
    q4 =Forest_Division.objects.get(id=id)
    q4.delete()
    return HttpResponse("<script>alert('deleted....');window.location='/forestdivision'</script>")  


def forestStation(request):
    q1 =Forest_Division.objects.all()
    q3 = Forest_Station.objects.all()
    if 'submit' in request.POST:
        dname =request.POST['dname']
        sname = request.POST['sname']
        place = request.POST['place']
        contactno = request.POST['contactno']
        q2 = Forest_Station(station_name=sname,place=place,contact_number=contactno,FOREST_DIVISION_id=dname)
        q2.save()
        return HttpResponse("<script>alert('added....');window.location='/foreststation'</script>")  

    return render(request,"admin/forest_station.html",{'q3':q3,'q1':q1})

def update_station(request,id):
    q5 = Forest_Division.objects.all()
    q4 = Forest_Station.objects.get(id=id)
    if 'update' in request.POST:
        dname =request.POST['dname']
        sname = request.POST['sname']
        place = request.POST['place']
        contactno = request.POST['contactno']
        q4.FOREST_DIVISION_id=dname
        q4.station_name=sname
        q4.place=place
        q4.contact_number=contactno
        q4.save()
        return HttpResponse("<script>alert('updated....');window.location='/foreststation'</script>")  
    return render(request,'admin/forest_station.html',{'q4':q4,'q5':q5})

def delete_station(request,id):
    q5 =Forest_Station.objects.get(id=id)
    q5.delete()
    return HttpResponse("<script>alert('deleted....');window.location='/foreststation'</script>")  

def animalManagement(request):
    q1 =Forest_Division.objects.all()
    q2 = Animal.objects.all()
    if 'submit' in request.POST:
        dname=request.POST['dname']
        animal_name=request.POST['animal_name']
        image = request.FILES['image']
        description = request.POST['description']
        safetytip = request.POST['safetytip']
        q3 = Animal(animal_name=animal_name,animal_image=image,animal_description=description,safety_tips=safetytip,FOREST_DIVISION_id=dname)
        q3.save()
        return HttpResponse("<script>alert('added....');window.location='/animals'</script>")  

    return render(request,"admin/animal_management.html",{'q1':q1,'q2':q2})

def update_animal(request,id):
    q5 = Animal.objects.get(id=id)  
    q4 = Forest_Division.objects.all()
    if 'update' in request.POST:
        dname=request.POST['dname']
        animal_name=request.POST['animal_name']

        # image = request.FILES['image']
        description = request.POST['description']
        safetytip = request.POST['safetytip']

        if 'image' in request.FILES:
            image=request.FILES['image']
            q5.animal_image=image
            q5.save()


        q5.animal_name=animal_name
        # q5.animal_image=image
        q5.animal_description=description
        q5.safety_tips=safetytip
        q5.FOREST_DIVISION_id=dname
        q5.save()
        return HttpResponse("<script>alert('updated....');window.location='/animals'</script>")  
    return render(request,'admin/animal_management.html',{'q4':q4,'q5':q5})

def delete_animal(request,id):
    q6 = Animal.objects.get(id=id)
    q6.delete()
    return HttpResponse("<script>alert('deleted....');window.location='/animals'</script>")  

def forestOfficer(request):
    q3 = Forest_Officer.objects.all()
    if 'submit' in request.POST:
        fname = request.POST['fname']
        lname = request.POST['lname']
        place = request.POST['place']
        phno = request.POST['phno']
        email = request.POST['email']
        password = request.POST['password']
        designation = request.POST['designation']
        q1 = Login(username=email,password=password,usertype='forest_officer')
        q1.save()
        q2 = Forest_Officer(fname=fname,lname=lname,place=place,phone=phno,email=email,designation=designation,LOGIN_id=q1.pk)
        q2.save()    
        return HttpResponse("<script>alert('added....');window.location='/forestofficer'</script>")  
    return render(request,'admin/forest_officer.html',{'q3':q3})

def sendNotification(request):
    q2 = Notification.objects.all()
    if 'submit' in request.POST:
        title = request.POST['title']
        tdescription = request.POST['tdescription']
        date = request.POST['date']
        q1 = Notification(title=title,description=tdescription,date=date)
        q1.save()
        return HttpResponse("<script>alert('added....');window.location='/notifications'</script>")  
    return render(request,"admin/admin_notifications.html",{'q2':q2})

def delete_notification(request,id):
    q3 = Notification.objects.get(id=id)
    q3.delete()
    return HttpResponse("<script>alert('deleted....');window.location='/notifications'</script>")  

def update_officer(request,id):
    q4 = Forest_Officer.objects.get(id=id)
    if 'update' in request.POST:
        fname = request.POST['fname']
        lname = request.POST['lname']
        place = request.POST['place']
        phno = request.POST['phno']
        designation = request.POST['designation']
        q4.fname=fname
        q4.lname=lname
        q4.place=place
        q4.phone=phno
        q4.designation=designation
        q4.save()
        return HttpResponse("<script>alert('updated....');window.location='/forestofficer'</script>")  
    return render(request,'admin/forest_officer.html',{'q4':q4})

def delete_officer(request,id):
    q5 =Forest_Officer.objects.get(id=id)
    q5.delete()
    return HttpResponse("<script>alert('deleted....');window.location='/forestofficer'</script>")  


def sendAlert(request):
    q2 = Alert.objects.filter(FOREST_OFFICER_id=request.session.get('fid'))
    if 'submit' in request.POST:
        alerttext = request.POST['alerttext']
        date = request.POST['date']
        q1 = Alert(description=alerttext,date=date,FOREST_OFFICER_id=request.session['fid']) 
        q1.save()
        return HttpResponse("<script>alert('added....');window.location='/alerts'</script>")   
    return render(request,"forest_officer/officer_send_alert.html",{'q2':q2})

def delete_alert(request,id):
    q3 = Alert.objects.get(id=id)
    q3.delete()
    return HttpResponse("<script>alert('deleted...');window.location='/alerts'</script>")

def view_complaint(request):
    q2 = Complaints.objects.all()
    return render(request,"admin/view_complaints.html",{'q2':q2})

def send_reply(request,id):
    q3 = Complaints.objects.get(id=id)
    if 'submit' in request.POST:
        reply = request.POST['reply']
        q3.reply=reply
        q3.save()
        return HttpResponse("<script>alert('replied....');window.location='/complaints'</script>")  
    return render(request,"admin/send_reply.html")

def emmergency_message(request):
    q1 = Emergency_Message.objects.all()
    return render(request,"forest_officer/view_emergency_message.html",{'q1':q1})

def officer_chat(request):
    q1 = Chat.objects.filter(FOREST_OFFICER_id=request.session['fid'])
    return render(request,"forest_officer/officer_chat.html")

def officer_complaint(request):
    q1 = Complaints.objects.all().order_by('date').values()
    return render(request,"forest_officer/officer_complaints.html",{'q1':q1})

def officer_notifications(request):
    q1 = Notification.objects.all().order_by('date').values()
    return render(request,"forest_officer/officer_notifications.html",{'q1':q1})



def logout(request):
    confirmation_script = """
        var confirmLogout = confirm('Do you want to proceed to logout?');
        if (confirmLogout) {
        window.location='/';
        }
        else{
        window.history.back();
        }
    """
    return HttpResponse("<script>" + confirmation_script + "</script>")