# jddown
Java Discord Markdown AST Parser.

```
We want to thank the Concordia University Part-time Faculty Association for the Professional Development Grant that allows us to develop/contribute to the open source community. 
```

Follows is some example usages.

## Streamed Usage

The `stream` method can be used to parse all the fragments in one shot.

```java
DiscordTextParser parser = new DiscordTextParser("**Discord** __Text__ ~~To~~ *Parse* :happy:.");

parser.stream(fragment -> {
    System.out.println(fragment);
});

```

## Serial Usage

A specific pattern can be checks using the `peek` and `next` commands.

```java
DiscordTextParser parser = new DiscordTextParser("**Please Pass**.");

if (parser.next().getText().equals("Please Pass")) {
    System.out.println("Passed");
}
```