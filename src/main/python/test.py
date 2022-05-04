import vt
import hashlib
import time

client = vt.Client("e9481497c3198559e299e966ec79be2ece56bf903cd0308474f8e3059aad6ee5")

def search_file(file_path = ""):
    try:
        with open(file_path,"rb") as f:
            # C:\Users\Digital\New folder\file\.gitignore
            bytes = f.read() # read the entire file as bytes
            readable_hash = hashlib.sha256(bytes).hexdigest()
        file = client.get_object("/files/" + readable_hash)
        x = str(file.last_analysis_stats)
        check(x,109)
    except:
        print("oh. it's a new file!!")
        upload_file(file_path)



def upload_file(file_path = ""):
    print("wait2")
    with open(file_path, "rb") as f:
        print("wait")
        analysis = client.scan_file(f, wait_for_completion=True)
    print("wait3")
    while True:
        print("checking")
        analysis = client.get_object("/analyses/{}", analysis.id)
        print(analysis.status)
        if analysis.status == "completed":
            search_file(file_path)
            break
        time.sleep(10)



def check(ana,n):
    if ana[n] + ana[n+1] + ana[n+2] + ana[n+3] + ana[n+4] + ana[n+5] + ana[n+6] + ana[n+7] + ana[n+8] == "malicious":
        t = ana[n + 12]
    elif ana[n+1] + ana[n+2] + ana[n+3] + ana[n+4] + ana[n+5] + ana[n+6] + ana[n+7] + ana[n+8] + ana[n+9] == "malicious":
        t = ana[n + 13]
    elif ana[n+2] + ana[n+3] + ana[n+4] + ana[n+5] + ana[n+6] + ana[n+7] + ana[n+8] + ana[n+9] + ana[n+10] == "malicious":
        t = ana[n + 14]
    elif ana[n+3] + ana[n+4] + ana[n+5] + ana[n+6] + ana[n+7] + ana[n+8] + ana[n+9] + ana[n+10] + ana[n+11] == "malicious":
        t = ana[n + 15]
    elif ana[n+4] + ana[n+5] + ana[n+6] + ana[n+7] + ana[n+8] + ana[n+9] + ana[n+10] + ana[n+11] + ana[n+12] == "malicious":
        t = ana[n + 16]
    elif ana[n+5] + ana[n+6] + ana[n+7] + ana[n+8] + ana[n+9] + ana[n+10] + ana[n+11] + ana[n+12] + ana[n+13] == "malicious":
        t = ana[n + 17]
    elif ana[n+6] + ana[n+7] + ana[n+8] + ana[n+9] + ana[n+10] + ana[n+11] + ana[n+12] + ana[n+13] + ana[n+14] == "malicious":
        t = ana[n + 18]
    if t == "0":
        print("file is safe")
    else:
        print("file isn't safe")



search_file()
