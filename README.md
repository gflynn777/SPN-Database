# SPN-Database
A fully-featured site that allows students and professors to create accounts and make, approve, or deny requests for special
permission numbers.

# Usage
Professors and students can sign in or create an account with their specifi

This program allows students to requests special permission numbers
from professors to enroll in classes. It starts off at the main login screen.
If a user does not have an account they may create one. For demonstration purposes, the option is given to create an
account for a Student or a Professor. Upon account creation, a netid is generated with the
first letter of the user's first and last names. The next available number is then affixed to the end of of the id.
<br><br>
The appropriate "Welcome Page" is loaded based on whether the user is a student, professor, or admin. 
<br>
# Student Page
A page that allows a user to create a new SPN
request, check the status of current SPN's, modify a pending request (delete the current SPN
requests they are waiting on), register for a class using an SPN, and look at the prerequisites for
each course in the database. Each student has a transcript with all the courses, course grades, gpa,
major/minor, class year, number of credits and the number of cs credits for the student. If the
student tries to request an SPN for a class it will be denied if they have not satisfied the
prerequisite for the course.
If the user were to choose a Professor account then the page they would be taken to is the
# Professor Page
From here, a professor can add a course, change the message that appears to
students once they request an SPN, retrieve the list of SPN's generated that are assigned and
available to be used, and approve or deny SPN requests (via the dropdown menu on the page).
The students in the dropdown menu with open requests are ranked by the weight of the credits
and the gpa along with the major and minor they are going to school for from highest priority to
lowest. 
# Admin Page
This page allows an administrator to add and delete courses,
assign a teacher to a course, and create new administrators.
Whenever an admin creates a course 10 SPN's are randomly generated. Initially, are all of the SPNs are
available and as students make their requests and the SPN's are assigned, the student's netID
is added to the SPN table under that SPN.
