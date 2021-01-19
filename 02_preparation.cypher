// Converting the roles to a list
MATCH (Employment)-[r:ASSIGNED_PROJECT]->(:Project) SET r.roles = split(r.roles, ",");