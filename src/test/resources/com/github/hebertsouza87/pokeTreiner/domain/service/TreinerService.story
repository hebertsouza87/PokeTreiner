Scenario: Register a new treiner
Given a treiner with name Ash, email ash@example.com and age 20
When the treiner is registered
Then the treiner should be saved with name Ash, email ash@example.com and age 20