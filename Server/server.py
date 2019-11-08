from flask import Flask, render_template, request, redirect, url_for
import threading
import subprocess
import psutil

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
  
def run():
   global p 
   p = subprocess.Popen(['java', '-jar', '../../t.jar']).pid

def kill(proc_pid):
   process = psutil.Process(proc_pid)
   for proc in process.children(recursive=True):
      proc.kill()
   process.kill()

thread = threading.Thread(target=run)

@app.route("/<action1>")
def action1(action1):
   # Convert the pin from the URL into an integer:
   global message
   if action1 == "enable":
      message = "enabled"
      thread.start()
      return message
   elif action1 == "disable":
      message = "disabled"
      kill(p)
      return message
   else:
      message = ""

   templateData = {
      'message' : message,
   }

if __name__ == "__main__":
   app.run(host='0.0.0.0', port=80, debug=True)
