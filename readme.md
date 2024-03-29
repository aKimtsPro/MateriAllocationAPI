# Run the app

## Run project locally:

Clone the project.
Run the `run_dev` batch file.

OR

In the `application.yml` file of the project, replace the references to 
environnement variables with values that work for your local environnement (connection postgres).

You can the run the project as you wish.

## Recreate image locally:



Run the following commands

1) package the app: `mvn clean install -DskipTests -f pom.xml`

2) to create the image: `docker build -t <your-image-name>:<tag> .`

Then follow the steps in `Run the materialloc_api image:` section

Before packaging the app, you might also want to replace the references to environnement variable
in the `application.yml` of the app with value that work in your local environnement.
If you do, there is no need to setup environnement variable in the next section.

## Run the materialloc_api image:

If you take the image from dockerhub:

    docker run -env ... akimtspro/materialloc:0.0.1

If you recreate the image:

    docker run [-env ...] <your-image-name>:<tag>

If you're using the dockerhub hosted image or did not replace the reference to environnement variables in the `application.yml`, the following environnement variable must be setup:
<dl>
    <dt>
        <p><strong>POSTGRES_HOST:</strong></p>
    </dt>
    <dd>
        <p>the host of the postgresql server that holds the database</p>        
    </dd>
    <dt>
        <p><strong>POSTGRES_PORT:</strong></p>
    </dt>
    <dd>
        <p>the port of the postgresql server that holds the database</p>        
    </dd>
    <dt>
        <p><strong>POSTGRES_DB:</strong></p>
    </dt>
    <dd>
        <p>the name of your database</p>        
    </dd>
    <dt>
        <p><strong>POSTGRES_USERNAME:</strong></p>
    </dt>
    <dd>
        <p>the username to use in order to connect the app to the database</p>        
    </dd>
    <dt>
        <p><strong>POSTGRES_PASSWORD:</strong></p>
    </dt>
    <dd>
        <p>the password to use in order to connect the app to the database</p>        
    </dd>
    <dt>
        <p><strong>EMAIL_ADDRESS:</strong></p>
    </dt>
    <dd>
        <p>the gmail email address that will send the emails for your app</p>        
    </dd>
    <dt>
        <p><strong>JWT_SECRET:</strong></p>
    </dt>
    <dd>
        <p>The secret for the signature of the access token</p>        
    </dd>
    <dt>
        <p><strong>JWT_REFRESH_SECRET:</strong></p>
    </dt>
    <dd>
        <p>The secret for the signature of the refresh token</p>        
    </dd>
</dl>

## Run with docker-compose

The docker structure:

![alt text](materialloc_stuct.PNG)

Alternatively, set them in the docker-compose.yml. If you do, be sure to have consistency with the postgres environnement.</br>

To do so, create a api.env file and a db.env file at the same level as your `docker-compose.yml` and setup values for these variables:

### api.env

    # postgres materiallo_db connection
    POSTGRES_HOST=materialloc_db
    POSTGRES_PORT=5432
    POSTGRES_DB=?
    POSTGRES_USERNAME=?
    POSTGRES_PASSWORD=?
    
    # spring email config
    EMAIL_ADDRESS=?
    EMAIL_PASSWORD=?
    
    # jwt config
    JWT_SECRET=?

### db.env

    POSTGRES_DB=?
    POSTGRES_USER=?
    POSTGRES_PASSWORD=?

Make sure the values are consistent between the files.

Then you can run:
    
    docker-compose up
