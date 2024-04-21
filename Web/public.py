from flask import*
from database import*

public=Blueprint('public',__name__)


@public.route('/')
def home():
    return render_template("home.html")



@public.route('/login',methods=['get','post'])
def login():
    if'sub'in request.form:
        username=request.form['uname']
        password=request.form['pass']
        
        qry="select * from login where username='%s' and password='%s'"%(username,password)
        res=select(qry)
        
        if res:
            session['log']=res[0]['login_id']
        
            if res[0]['usertype']=='admin':
                return ("<script>alert('WELCOME ADMIN');window.location='/adminhome'</script>")
            
            if res[0]['usertype']=='officer':
                qry1="select * from forest_officer where login_id='%s'"%(session['log'])
                res1=select(qry1)
                if res1:
                    session['officer']=res1[0]['officer_id']
                    return ("<script>alert('WELCOME OFFICER');window.location='/forest_home'</script>")
        else:
            return ("<script>alert('invalid username or password');window.location='/login'</script>")


         
    return render_template("login.html")