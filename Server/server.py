from flask import Flask, render_template, request, redirect, url_for
import Humanoid
import threading
import subprocess

app = Flask(__name__)

@app.route('/')
def index():
    return render_template('main.html')
   
@app.route("/")
def main():
   templateData = {
      'message': ""
      }
   # Pass the template data into the template main.html and return it to the user
   return message
  
# def walkForward():
#    humanoid.walkForward()

# def turnLeft():
#    humanoid.turnLeft()


@app.route("/<action1>")
def action1(action1):
   # Convert the pin from the URL into an integer:
   global message
   if action1 == "enable":
      message = "enabled"
      # thread = threading.Thread(target=walkForward)
      # thread.start()
      p = subprocess.Popen(['java', '-jar', 'Blender.jar'])
      return message 
   elif action1 == "disable":
      message = "disabled"
      # thread = threading.Thread(target=turnLeft)
      # thread.start()
      p.kill()
      return message
   else:
      message = ""

   templateData = {
      'message' : message,
   }

if __name__ == "__main__":
   app.run(host='0.0.0.0', port=80, debug=True)
