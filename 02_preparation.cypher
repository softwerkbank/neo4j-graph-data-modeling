// Converting the roles to a list
MATCH (Employment)-[r:ASSIGNED_PROJECT]->(:Project) SET r.roles = split(r.roles, ",");

// Converting tags to a list
MATCH (k:Knowledge) WHERE EXISTS(k.tags) SET k.tags = split(k.tags, ",");
MATCH (s:Skill) WHERE EXISTS(s.tags) SET s.tags = split(s.tags, ",");

// Converting date string to date
MATCH (emp:Employment) 
SET emp.start_date = date(emp.start_date) 
SET emp.end_date = date(emp.end_date);

MATCH (e:Employee) 
SET e.birthday = date(e.birthday);

MATCH ()-[rel:ASSIGNED_PROJECT]-() 
SET rel.start_date = date(rel.start_date)
SET rel.end_date = date(rel.end_date);

MATCH ()-[rel:PARTICIPATED_COURSE]-() 
SET rel.start_date = date(rel.start_date)
SET rel.end_date = date(rel.end_date);

MATCH ()-[rel:PASSED_EXAM]-() 
SET rel.examination_date = date(rel.examination_date);