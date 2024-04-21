from flask import*
from database import*
import uuid

from Animal_Video import *
from camera import*



forest_officer=Blueprint('forest_officer',__name__)


@forest_officer.route('/forest_home')
def forest_home():
    return render_template("forest_home.html")


@forest_officer.route('/officer_viewnotification')
def officer_viewnotification():
    data={}
    qry="select * from notifications"
    res=select(qry)
    if res:
        data['view']=res
    qry2="select * from notifications"
    res2=select(qry2)
    data['demo']=res2
    return render_template("officer_viewnotification.html",data=data)


@forest_officer.route('/officer_viewcomplaints')
def officer_viewcomplaints():
    data={}
    qry="select * from complaints inner join user using(user_id) where reply='pending'"
    res=select(qry)
    if res:
        data['view']=res
        
        if'action'in request.args:
            action=request.args['action']
            id=request.args['id']
            if action=='forward':
                qry2="update complaints set reply='checked' where complaints_id='%s'"%(id)
                update(qry2)
                return ("<script>alert('FORWARDED');window.location='/officer_viewcomplaints'</script>")

    return render_template("officer_viewcomplaints.html",data=data)


@forest_officer.route('/viewcamera')
def viewcamera():
    data={}
    qry="select * from camera inner join allocate using(forest_station_id) where officer_id='%s'"%(session['officer'])
    res=select(qry)
    if res:
        data['view']=res
    return render_template("officer_viewcamera.html",data=data) 


@forest_officer.route('/startcamera')
def startcamera():
    id=request.args['id']
    data={}
    qry="select * from camera where camera_id='%s'"%(id)
    res=select(qry)
    if res:
        data['view']=res
    
        
       


        
       
            
    return render_template("chooseoption.html",data=data)



@forest_officer.route('/file',methods=['get','post'])
def file():
    id=request.args['id']
    if'submit'in request.form:
        path=request.files['video']
        img="C:\\RISS_PROJECTS\\Wild_Animal\\Web\\static\\" +str(uuid.uuid4())+path.filename
        path.save(img)
        print(path,img)
        if img:
            print("##########",img)
            print("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@")
            capture(img,id)
        
        
    return render_template('fileupload.html')


@forest_officer.route('/on')
def on():
    id=request.args['id']

    cam(id)
   
   
 


