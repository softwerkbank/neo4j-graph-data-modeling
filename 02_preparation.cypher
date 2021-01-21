// Converting the roles to a list
MATCH (Employment)-[r:ASSIGNED_PROJECT]->(:Project) SET r.roles = split(r.roles, ",");

// Converting tags to a list
MATCH (k:Knowledge) WHERE EXISTS(k.tags) SET k.tags = split(k.tags, ",");