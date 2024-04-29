# Ideas

- built in types for params: validator - processor - modifier
    - validator: validates the JsonNode and all its children
    - processor: processes the JsonNode's value and has permission to edit it
    - modifier: modifies the params of the JsonNode - can add or edit params
- removal of the spring dependency
- customizability: adding unique params to the config
- debugging: ability to check the new compiled type
- precedence: ability to choose what happens if params override each other