# RemoveSpaces

RemoveSpace is a Java class that implements methods to remove space from a BufferedImage.

<br>

## Sample

#### Input Image
We get the following Image as input and then we show the results using the methods implemented.  
<kbd>
![Original BufferedImage](https://github.com/simoska4/removespaces/blob/master/sample/input.png)
</kbd>

#### Remove spaces - only externally
This method remove spaces from the input BufferedImage only externally
``BufferedImage compress = RemoveSpaces.compress((image, true);``  
<kbd>
![Original BufferedImage](https://github.com/simoska4/removespaces/blob/master/sample/compress_onlyexternally.png)
</kbd>

#### Remove spaces - also internally
This method remove spaces from the input BufferedImage also internally
``BufferedImage compress = RemoveSpaces.compress((image, false);``  
<kbd>
![Original BufferedImage](https://github.com/simoska4/removespaces/blob/master/sample/compress.png)
</kbd>


##  Usage

#### Apache Maven  
```markdown
<dependency>
  <groupId>com.github.simoska4.removespaces</groupId>
  <artifactId>remove-spaces</artifactId>
  <version>1.0</version>
</dependency>
```


#### Gradle Groovy DSL  
```markdown
implementation 'com.github.simoska4.removespaces:remove-spaces:1.0'
```


#### Gradle Kotlin DSL 
```markdown
compile("com.github.simoska4.removespaces:remove-spaces:1.0")
```


#### Scala SBT 
```markdown
libraryDependencies += "com.github.simoska4.removespaces" % "remove-spaces" % "1.0"
```


#### Apache Ivy
```markdown
<dependency org="com.github.simoska4.removespaces" name="remove-spaces" rev="1.0" />
```


#### Groovy Grape
```markdown
@Grapes(
  @Grab(group='com.github.simoska4.removespaces', module='remove-spaces', version='1.0')
)
```


#### Leiningen
```markdown
[com.github.simoska4.removespaces/remove-spaces "1.0"]
```


#### Apache Buildr
```markdown
'com.github.simoska4.removespaces:remove-spaces:jar:1.0'
```


## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.


## License
[MIT](https://choosealicense.com/licenses/mit/)
