from flask import*
from database import*
from admin import admin
from forest_officer import forest_officer
from public import public
from api import api

app=Flask(__name__)
app.secret_key="wild"
app.register_blueprint(admin)
app.register_blueprint(forest_officer)
app.register_blueprint(public)
app.register_blueprint(api)



app.run(debug=True,port=5047,host="0.0.0.0")