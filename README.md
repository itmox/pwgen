# pwgen
a generator Which transforms weak passwords into stronger ones.
The stronger passwords are easy to remember. 
The generated passwords are checked against online Rainbowtables to ensure that they are not broken already.

IMPORTANT:
Even if pwgen transforms insecure passwords into secure ones the insecure password must still stay secure!!!

The insecure password can be a word from the dictionary. The more secure one is based on the insecure password so you can remember the password easy.
During the password check against rainbow tables the password remains on your machine. Only the password-hashes are send to check the strength.

IMPORTANT:
If you check your password online against a Rainbow table you should hash the password offline and check only the hash online. If you use a online tool to generate the hash to the password the tool might write your password with the hash to its database.
