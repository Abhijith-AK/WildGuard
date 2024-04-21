from flask import*
from database import*
import uuid

admin=Blueprint('admin',__name__)


@admin.route('/adminhome')
def adminhome():
    return render_template("adminhome.html")


@admin.route('/adminmanage_forestdiv',methods=['get','post'])
def adminmanage_forestdiv():
    data={}
    qry1="select * from forest_division"
    res1=select(qry1)
    if res1:
        data['view']=res1
    if'submit'in request.form:
        divisionname=request.form['division']
        
        qry="insert into forest_division values(null,'%s')"%(divisionname)
        res=insert(qry)
        return ("<script>alert('Added Successfully');window.location='/adminmanage_forestdiv'</script>")

        
    if 'action'in request.args:
        action=request.args['action']
        id=request.args['id']
        
        if action=='update':
            qry2="select * from forest_division where forest_division_id='%s'"%(id)
            res2=select(qry2)
            if res2:
                data['up']=res2
                
                if'update'in request.form:
                    divisionname=request.form['division']
                    
                    qry3="update forest_division set division_name='%s' where forest_division_id='%s'"%(divisionname,id)
                    update(qry3)
                    return ("<script>alert('Successfully Updated');window.location='/adminmanage_forestdiv'</script>")

        
        
        if action=='delete':
            qry4="delete from forest_division where forest_division_id='%s'"%(id)  
            delete(qry4)
            return ("<script>alert('Deleted Successfully');window.location='/adminmanage_forestdiv'</script>")

                

                    
            
    return render_template("adminmanage_forestdiv.html",data=data)



@admin.route('/adminmanage_foreststation',methods=['get','post'])
def adminmanage_foreststation():
    data={}
    qry1="select * from forest_division"
    res1=select(qry1)
    if res1:
        data['forest']=res1
    if'submit'in request.form:
        divid=request.form['divid']
        station=request.form['station']
        place=request.form['place']
        cnum=request.form['cnum']
        qry="insert into forest_station values(null,'%s','%s','%s','%s')"%(divid,station,place,cnum)
        insert(qry)
        return ("<script>alert('Added Successfully');window.location='/adminmanage_foreststation'</script>")
    
    qry3="select * from forest_station inner join forest_division using(forest_division_id)"
    res3=select(qry3)
    if res3:
        data['view']=res3
        
    if 'action'in request.args:
        action=request.args['action']
        id=request.args['id']
        
        if action=='update':
            qry4="select * from forest_station where forest_station_id='%s'"%(id)
            res4=select(qry4)
            if res4:
                data['up']=res4
                if'update'in request.form:
                    station=request.form['station']
                    place=request.form['place']
                    cnum=request.form['cnum']
                    
                    qry5="update forest_station set station_name='%s',place='%s',contact_number='%s' where forest_station_id='%s'"%(station,place,cnum,id)
                    update(qry5)
                    return ("<script>alert('Successfully Updated');window.location='/adminmanage_foreststation'</script>")

        if action=='delete':
            qry6="delete from forest_station where forest_station_id='%s'"%(id)
            delete(qry6)
            return ("<script>alert('Deleted Successfully');window.location='/adminmanage_foreststation'</script>")

                     
                    

    return render_template("adminmanage_foreststation.html",data=data)



@admin.route('/adminmanage_animal',methods=['get','post'])
def adminmanage_animal():
    data={}
    qry1="select * from forest_division"
    res1=select(qry1)
    if res1:
        data['forest']=res1
        
        
    if'submit'in request.form:
        divid=request.form['divid']
        animal=request.form['animal']
        path=request.files['image']
        img="static/"+str(uuid.uuid4())+path.filename
        path.save(img)
        
        
        qry="insert into animal values(null,'%s','%s','%s')"%(divid,animal,img)
        insert(qry)
        return ("<script>alert('Added Successfully');window.location='/adminmanage_animal'</script>")
    
    qry2="select * from animal inner join forest_division using(forest_division_id)"
    res2=select(qry2)
    if res2:
        data['view']=res2
        
    if 'action'in request.args:
        action=request.args['action']
        id=request.args['id']
        if action=='delete':
            qry3="delete from animal where animal_id='%s'"%(id)
            delete(qry3)
            return ("<script>alert('DELETED Successfully');window.location='/adminmanage_animal'</script>")

            
            



        
    
    return render_template("adminmanage_animal.html",data=data) 



@admin.route('/adminmanage_panimals',methods=['get','post'])
def adminmanage_panimals():
    data={}
    qry2="select * from animal inner join forest_division using(forest_division_id)"
    res2=select(qry2)
    if res2:
        data['view']=res2
        if 'action'in request.args:
            action=request.args['action']
            id=request.args['id']
            
            if action=='add':
                qry="insert into preserved_animals values(null,'%s')"%(id)
                insert(qry)
                return ("<script>alert('Added Successfully');window.location='/adminmanage_panimals'</script>")
            
            
    qry3="select * from preserved_animals inner join animal using(animal_id) inner join forest_division using(forest_division_id)"
    res3=select(qry3)
    if res3:
        data['pview']=res3
        if 'action'in request.args:
            action=request.args['action']
            id=request.args['id']
            
            if action=='remove':
                qry4="delete from preserved_animals where animal_id='%s'"%(id)
                delete(qry4)
                return ("<script>alert('Removed Successfully');window.location='/adminmanage_panimals'</script>")

                
        
            

                
            
          
    return render_template("adminmanage_panimals.html",data=data) 




@admin.route('/adminmanage_officer',methods=['get','post'])
def adminmanage_officer():
    data={}
    qry3="select * from forest_officer"
    res3=select(qry3)
    if res3:
        data['view']=res3
    if'submit'in request.form:
        fname=request.form['fname']
        lname=request.form['lname']
        place=request.form['place']
        phone=request.form['phone']
        email=request.form['email']
        designation=request.form['designation']
        uname=request.form['uname']
        password=request.form['pass']
        
        qry="insert into login values(null,'%s','%s','officer')"%(uname,password)
        res=insert(qry)
        qry2="insert into forest_officer values(null,'%s','%s','%s','%s','%s','%s','%s')"%(res,fname,lname,place,phone,email,designation)
        insert(qry2)
        return ("<script>alert('Added Successfully');window.location='/adminmanage_officer'</script>")
    
    if 'action'in request.args:
        action=request.args['action']
        id=request.args['id']
        
        if action=='update':
            qry4="select * from forest_officer where officer_id='%s'"%(id)
            res4=select(qry4)
            if res4:
                data['officerview']=res4
                
                if'update'in request.form:
                    fname=request.form['fname']
                    lname=request.form['lname']
                    place=request.form['place']
                    phone=request.form['phone']
                    email=request.form['email']
                    designation=request.form['designation']
                    
                    
                    qry5="update forest_officer set fname='%s',lname='%s',place='%s',phone='%s',email='%s',designation='%s' where officer_id='%s'"%(fname,lname,place,phone,email,designation,id)
                    update(qry5)
                    return ("<script>alert('UPDATED Successfully');window.location='/adminmanage_officer'</script>")
                
        if action=='delete':
            qry6="delete from forest_officer where officer_id='%s'"%(id)
            delete(qry6)      
            return ("<script>alert('Deleted Successfully');window.location='/adminmanage_officer'</script>")
  

    

    return render_template("adminmanage_officer.html",data=data)



@admin.route('/admin_notification',methods=['get','post'])
def admin_notification():
 
    if 'submit'in request.form:
        title=request.form['title']
        des=request.form['des']
        
        qry="insert into notifications values(null,'%s','%s',curdate())"%(title,des)
        insert(qry)
        return ("<script>alert('Send Successfully');window.location='/admin_notification'</script>")

        
        
    return render_template("admin_notification.html")   


@admin.route('/adminallocation',methods=['get','post'])
def adminallocation():
    data={}
    qry="select * from forest_station inner join forest_division using(forest_division_id)"
    res=select(qry)
    if res:
        data['view']=res
    qry2="select * from forest_officer"
    res2=select(qry2)  
    if res2:
        data['off']=res2   
    if'submit'in request.form:
        station=request.form['station']
        off=request.form['off']   
        qry3="insert into allocate values(null,'%s','%s','pending')"%(off,station)
        insert(qry3)
    
    qry4="SELECT * FROM allocate LEFT JOIN forest_officer USING(officer_id) INNER JOIN forest_station USING(forest_station_id)"
    res4=select(qry4)
    if res4:
        data['allocated']=res4
    if'action'in request.args:
        action=request.args['action']
        id=request.args['id']
        if action=='remove':
            qry5="delete from allocate where allocate_id='%s'"%(id)
            delete(qry5)
            return ("<script>alert('REMOVED SUCCESSFULLY');window.location='/adminallocation'</script>")

            
                
    
  
    
    return render_template("adminallocation.html",data=data)


# @admin.route('/officerallocation')
# def officerallocation():
#     if 'id1' in request.args:
#         id1=request.args['id1']
#     data={}
#     qry2="select * from forest_officer"
#     res2=select(qry2)
    

#     if res2:
#         data['viewoff']=res2
#     if 'action' in request.args:
#         action=request.args['action']
#         id=request.args['id']
#         if action=='assign':
#             qry="insert into allocate values(null,'%s','%s','pending')"%(id,id1)
#             insert(qry)
        

#     return render_template("officerallocation.html",data=data)




@admin.route('/admin_contactdetails',methods=['get','post'])
def admin_contactdetails():
    data={}
    qry2="select * from contact_details"
    res2=select(qry2)
    if res2:
        data['view']=res2
    if'submit'in request.form:
        name=request.form['name']
        num=request.form['num']
        
        qry="insert into contact_details values(null,'%s','%s')"%(name,num)
        insert(qry)
        return ("<script>alert('Details Added');window.location='/admin_contactdetails'</script>")
    if'action'in request.args:
        action=request.args['action']
        id=request.args['id']
        if action=='update':
            qry3="select * from contact_details where contact_id='%s'"%(id)
            res3=select(qry3)
            if res3:
                data['up']=res3
                
                if'update'in request.form:
                    name=request.form['name']
                    num=request.form['num']
                    
                    qry4="update contact_details set name='%s',contact='%s' where contact_id='%s'"%(name,num,id)
                    update(qry4)
                    return ("<script>alert('Details Updated');window.location='/admin_contactdetails'</script>")
                
        if action=='delete':
            qry5="delete from contact_details where contact_id='%s'"%(id)
            delete(qry5)
            return ("<script>alert('Details Deleted');window.location='/admin_contactdetails'</script>")

            

                    
                    

        
    return render_template("admin_contactdetails.html",data=data)




@admin.route('/admincomplaint',methods=['get','post'])
def admincomplaint():
    data={}
    qry="select * from complaints inner join user using(user_id) where reply='checked'"
    res=select(qry)
    if res:
        data['view']=res
        if 'action'in request.args:
            action=request.args['action']
            id=request.args['id']
            if action=='reply':
                qry2="select * from complaints where complaints_id='%s'"%(id)
                res2=select(qry2)
                if res:
                    data['reply']=res2
                    if'reply'in request.form:
                        reply=request.form['reply']
                        qry3="update complaints set reply='%s' where complaints_id='%s'"%(reply,id)
                        res3=update(qry3)
                        return ("<script>alert('Replyed ');window.location='/admincomplaint'</script>")

                    
    return render_template("admincomplaint.html",data=data)




@admin.route('/managecamera',methods=['get','post'])
def managecamera():
    id=request.args['id']
    if'submit'in request.form:
        cname=request.form['cname']
        latitude=request.form['latitude']
        longitude=request.form['longitude']
        
        qry="insert into camera values(null,'%s','%s','%s','%s')"%(id,cname,latitude,longitude)
        insert(qry)
        return ("<script>alert('ADDED ');window.location='/adminmanage_foreststation'</script>")

        
    return render_template("managecamera.html") 