// Extract position from employments
CREATE CONSTRAINT unique_position_name ON (p:Position) ASSERT p.name IS UNIQUE;

CALL apoc.refactor.categorize('position', 'IN_POSITION', true, 'Position', 'name', [], 100);

// Extract roles from project assignments
CREATE CONSTRAINT unique_role_name ON (r:Role) ASSERT r.name IS UNIQUE;

MATCH (a:Assignment)
UNWIND a.roles AS role		// for each role in roles
MERGE (r:Role {name: role})	// Create role if not exist
MERGE (a)-[:IN_ROLE]->(r)	// Create relationship to role if not exist
REMOVE a.roles;				// Remove property "roles" from Assignment