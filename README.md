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


## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.


## License
[MIT](https://choosealicense.com/licenses/mit/)
