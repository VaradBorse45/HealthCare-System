import requests
import sys

url = 'http://localhost:9000/submit/'+sys.argv[1]
r = requests.get(url, allow_redirects=True)

open(sys.argv[1], 'wb').write(r.content)