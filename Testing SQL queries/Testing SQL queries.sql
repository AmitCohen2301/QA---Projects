
CREATE SCHEMA `exforafeka_1` ;


use `exforafeka_1` ;

CREATE TABLE students (
	student_id INT (11) UNSIGNED NOT NULL,
	student_name VARCHAR(25), student_city varchar(25),
	PRIMARY KEY (student_id)
	);


CREATE TABLE courses (
	cpk INT  UNSIGNED NOT NULL,
	course_id VARCHAR(25),
    course_prior VARCHAR(25),
	PRIMARY KEY (cpk)
	);


CREATE TABLE grades (
	gpk INT  UNSIGNED NOT NULL,
	student_id int,
   course_id VARCHAR(25),
    grade int,
 grade_year int,
	PRIMARY KEY (gpk)
	);


insert into students values (1,"Avi","haifa");
insert into students values (2,"Benny","tel-aviv");
insert into students values (3,"Gadi","jerusalem");
commit;


insert into courses values (1,"A","None");
insert into courses values (2,"B","A");
commit;

insert into grades values (1,1,"A",53,2022);
insert into grades values (2,3,"A",52,2022);
COMMIT;

select student_name, avggrad from students join
(select student_id, avg(grade) as avggrad from grades  group by student_id 
having avg(grade)>(select avg(grade) from grades where grade_year=2022)) as x
on students.student_id=x.student_id ;





#case 1: Grade year = 2022, one group,Average  greater than the general average 
insert into students values (1,"Avi","haifa");
COMMIT;

insert into courses values (1,"A","None");
insert into courses values (2,"B","A");
COMMIT;

insert into grades values (1,1,"A",100,2022);
insert into grades values (3,1,"B",90,2022);

select student_name, avggrad from students join
(select student_id, avg(grade) as avggrad from grades  group by student_id 
having avg(grade)>(select avg(grade) from grades where grade_year=2022)) as x
on students.student_id=x.student_id ;

#case 2: Grade year = 2022, one group,Average NULL

UPDATE grades 
SET grade = null 
WHERE gpk = 1;

UPDATE grades 
SET grade = null 
WHERE gpk = 3;

select student_name, avggrad from students join
(select student_id, avg(grade) as avggrad from grades  group by student_id 
having avg(grade)>(select avg(grade) from grades where grade_year=2022)) as x
on students.student_id=x.student_id ;

#case 3: Grade year  = 2021, one group, Average greater than the general average 

UPDATE grades 
SET grade_year = 2021 
WHERE gpk = 1;

UPDATE grades 
SET grade_year = 2021 
WHERE gpk = 3;

select student_name, avggrad from students join
(select student_id, avg(grade) as avggrad from grades  group by student_id 
having avg(grade)>(select avg(grade) from grades where grade_year=2022)) as x
on students.student_id=x.student_id ;

#case 4: Grade year  = NULL, one group, Average greater than the general average 
UPDATE grades 
SET grade_year = NULL 
WHERE gpk = 1;

UPDATE grades 
SET grade_year = NULL 
WHERE gpk = 3;


select student_name, avggrad from students join
(select student_id, avg(grade) as avggrad from grades  group by student_id 
having avg(grade)>(select avg(grade) from grades where grade_year=2022)) as x
on students.student_id=x.student_id ;


#case 5: Grade year  = 2022, Three group, Average greater than the general average 
insert into students values (2,"Benny","tel-aviv");
insert into students values (3,"Gadi","jerusalem");
commit;

insert into grades values (1,1,"A",53,2022);
insert into grades values (2,3,"A",52,2022);
insert into grades values (4,2,"B",52,2022);
commit;

select student_name, avggrad from students join
(select student_id, avg(grade) as avggrad from grades  group by student_id 
having avg(grade)>(select avg(grade) from grades where grade_year=2022)) as x
on students.student_id=x.student_id ;


#case 6: Grade year  = 2022, Three group, Average NULL

UPDATE grades 
SET grade = null 
WHERE gpk = 2;

UPDATE grades 
SET grade = null 
WHERE gpk = 1;


UPDATE grades 
SET grade = null 
WHERE gpk = 3;


UPDATE grades 
SET grade = null 
WHERE gpk = 4;

select student_name, avggrad from students join
(select student_id, avg(grade) as avggrad from grades  group by student_id 
having avg(grade)>(select avg(grade) from grades where grade_year=2022)) as x
on students.student_id=x.student_id ;

#case 7: Grade year  = 2021, Three group, Average greater than the general average 

UPDATE grades 
SET grade = 100 
WHERE gpk = 3;

UPDATE grades 
SET grade = 100 
WHERE gpk = 1;


UPDATE grades 
SET grade = 100 
WHERE gpk = 2;


UPDATE grades 
SET grade = 50 
WHERE gpk = 4;

UPDATE grades 
SET grade_year = 2021 
WHERE gpk = 2;

select student_name, avggrad from students join
(select student_id, avg(grade) as avggrad from grades  group by student_id 
having avg(grade)>(select avg(grade) from grades where grade_year=2022)) as x
on students.student_id=x.student_id ;

#case 8: Grade year  = NULL, Three group, Average greater than the general average 

UPDATE grades 
SET grade_year = 2022 
WHERE gpk = 3;

UPDATE grades 
SET grade_year = 2022 
WHERE gpk = 1;


UPDATE grades 
SET grade_year = null 
WHERE gpk = 2;


UPDATE grades 
SET grade_year = 2022 
WHERE gpk = 4;

select student_name, avggrad from students join
(select student_id, avg(grade) as avggrad from grades  group by student_id 
having avg(grade)>(select avg(grade) from grades where grade_year=2022)) as x
on students.student_id=x.student_id ;

#case 9: Grade year  = 2022, Three group, Average smaller than the general average 


UPDATE grades 
SET grade = 50 
WHERE gpk = 3;

UPDATE grades 
SET grade = 50 
WHERE gpk = 1;

UPDATE grades 
SET grade = 100 
WHERE gpk = 2;

select student_name, avggrad from students join
(select student_id, avg(grade) as avggrad from grades  group by student_id 
having avg(grade)>(select avg(grade) from grades where grade_year=2022)) as x
on students.student_id=x.student_id ;

#case 10: Grade year  = 2022, NULL group, Average bigger than the general average 

delete from students
where student_id=3;

delete from students
where student_id=2;

delete from students
where student_id=1;

select student_name, avggrad from students join
(select student_id, avg(grade) as avggrad from grades  group by student_id 
having avg(grade)>(select avg(grade) from grades where grade_year=2022)) as x
on students.student_id=x.student_id ;

#case 10: Grade year  = 2022, NULL group, Average Smaller than the general average 


UPDATE grades 
SET grade = 50 
WHERE gpk = 1;

UPDATE grades 
SET grade = 100 
WHERE gpk = 2;

select student_name, avggrad from students join
(select student_id, avg(grade) as avggrad from grades  group by student_id 
having avg(grade)>(select avg(grade) from grades where grade_year=2022)) as x
on students.student_id=x.student_id ;


#case 11: Grade year  = 2022, NULL group, NULL Average 
UPDATE grades 
SET grade = null 
WHERE gpk = 1;

UPDATE grades 
SET grade = null 
WHERE gpk = 3;


UPDATE grades 
SET grade = null 
WHERE gpk = 2;


UPDATE grades 
SET grade = null 
WHERE gpk = 4;

select student_name, avggrad from students join
(select student_id, avg(grade) as avggrad from grades  group by student_id 
having avg(grade)>(select avg(grade) from grades where grade_year=2022)) as x
on students.student_id=x.student_id ;

#case 12: Grade year  = 2021, NULL group, Average bigger than the general average 
UPDATE grades 
SET grade = 100 
WHERE gpk = 1;

UPDATE grades 
SET grade = 100 
WHERE gpk = 2;


UPDATE grades 
SET grade = 50 
WHERE gpk = 3;


UPDATE grades 
SET grade = 50 
WHERE gpk = 4;

UPDATE grades 
SET grade_year = 2021 
WHERE gpk = 2;

select student_name, avggrad from students join
(select student_id, avg(grade) as avggrad from grades  group by student_id 
having avg(grade)>(select avg(grade) from grades where grade_year=2022)) as x
on students.student_id=x.student_id ;

#case 13: Grade year  = NULL, NULL group, Average bigger than the general average 
UPDATE grades 
SET grade_year = null 
WHERE gpk = 2;

UPDATE grades 
SET grade_year = null 
WHERE gpk = 1;

UPDATE grades 
SET grade_year = null 
WHERE gpk = 3;

UPDATE grades 
SET grade_year = null 
WHERE gpk = 4;

select student_name, avggrad from students join
(select student_id, avg(grade) as avggrad from grades  group by student_id 
having avg(grade)>(select avg(grade) from grades where grade_year=2022)) as x
on students.student_id=x.student_id ;
 
insert into students values (2,"Benny","tel-aviv");
insert into students values (3,"Gadi","jerusalem");

UPDATE grades 
SET grade = 50 
WHERE gpk = 1;

UPDATE grades 
SET grade_year = 2022
WHERE gpk = 1;

select * from grades;
select * from courses;

#Adding a new student to course B without taking the pre-course A
insert into students values (4,"Ortal","tel-aviv"); 
commit;
insert into courses values (3,"B","A");
commit;
insert into grades values (5,4,"B",70,2022);

select student_name, avggrad from students join
(select student_id, avg(grade) as avggrad from grades  group by student_id 
having avg(grade)>(select avg(grade) from grades where grade_year=2022)) as x
on students.student_id=x.student_id ;