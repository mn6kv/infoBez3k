from Crypto.Cipher import AES
import string
import random

# нужно установить pycrypto
# при помощи команды pip install pycrypto или pipwin install pycrypto
# запустить код

ABC = string.ascii_uppercase
saltUltimate = ''

def makeRandomString(length: int) -> str:
    return ''.join(random.choices(ABC, k=length))

KEY = makeRandomString(16)
SALT = makeRandomString(16)
print('KEY:', KEY)
print('SALT:', SALT)

def f(x):
    cipher = AES.new(KEY, AES.MODE_ECB)
    x += SALT
    return cipher.encrypt(x[:16])

for i in reversed(range(16)):
    additionalString = 'A' * i
    encryptedAdditionalString  = f(additionalString)
    for char in ABC:
        if  encryptedAdditionalString == f(additionalString + saltUltimate + char):
            saltUltimate += char
            break


print('\nготовая соль :', saltUltimate)
