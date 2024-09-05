import requests
import sys
test_file = open(sys.argv[1], "rb")
test_url="http://localhost:9000/submit"
test_response = requests.post(test_url, files = {"v_file": test_file})
if test_response.ok:
    print("Upload completed successfully!")
    print(test_response.text)
else:
    print("Something went wrong!"+str(test_response))
