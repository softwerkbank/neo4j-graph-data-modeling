// Extract position from employments
CREATE CONSTRAINT unique_position_name ON (p:Position) ASSERT p.name IS UNIQUE;

CALL apoc.refactor.categorize('position', 'IN_POSITION', true, 'Position', 'name', [], 100);

// Extract roles from project assignments
CREATE CONSTRAINT unique_role_name ON (r:Role) ASSERT r.name IS UNIQUE;

MATCH (a:Assignment)
UNWIND a.roles AS role
MERGE (r:Role {name: role})
MERGE (a)-[:IN_ROLE]->(r)
REMOVE a.roles;