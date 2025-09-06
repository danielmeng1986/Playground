import { NestFactory } from '@nestjs/core';
import { DocumentBuilder, SwaggerModule } from '@nestjs/swagger';
import { ValidationPipe } from '@nestjs/common';
import { AppModule } from './app.module';
import { AllExceptionsFilter } from './common/filters/http-exception.filter';
import { writeFileSync } from 'fs';
import { dump } from 'js-yaml';
import { join } from 'path';

async function bootstrap() {
  const app = await NestFactory.create(AppModule);

  // Enable global exception filter
  app.useGlobalFilters(new AllExceptionsFilter());

  // Enable validation
  app.useGlobalPipes(new ValidationPipe());

  // Enable CORS
  app.enableCors();

  // Swagger configuration
  const config = new DocumentBuilder()
    .setTitle('City Playground API 1.0')
    .setDescription(
      'API for managing users, cities, playgrounds, and related activities.',
    )
    .setVersion('1.0.0')
    .setContact('yingchen', 'www.yingchen.de', 'yingchen.meng@web.de')
    .setLicense('playground', 'https://opensource.org/license/mit')
    .addServer('http://localhost:3000')
    .addBearerAuth()
    .build();

  const document = SwaggerModule.createDocument(app, config);
  SwaggerModule.setup('api', app, document);

  // Generate and save OpenAPI specification
  const openApiPath = join(process.cwd(), '..', 'openapi2.yaml');
  try {
    // eslint-disable-next-line @typescript-eslint/no-unsafe-call, @typescript-eslint/no-unsafe-assignment
    const originalDoc = document;

    // 重新组织文档结构，确保正确的顺序
    const orderedDoc = {
      openapi: originalDoc.openapi,
      info: originalDoc.info,
      servers: originalDoc.servers,
      paths: originalDoc.paths,
      components: originalDoc.components,
      tags: originalDoc.tags,
    };

    // eslint-disable-next-line @typescript-eslint/no-unsafe-call, @typescript-eslint/no-unsafe-assignment
    const yamlString = dump(orderedDoc, {
      noRefs: true,
      skipInvalid: true,
      sortKeys: false, // 保持我们定义的顺序
    });
    // eslint-disable-next-line @typescript-eslint/no-unsafe-argument
    writeFileSync(openApiPath, yamlString);
    console.log(`OpenAPI specification saved to: ${openApiPath}`);
  } catch (error) {
    console.error('Failed to generate OpenAPI YAML:', error);
  }

  await app.listen(process.env.PORT ?? 3000);
  console.log(`Application is running on: ${await app.getUrl()}`);
  console.log(
    `Swagger documentation is available at: ${await app.getUrl()}/api`,
  );
}

void bootstrap();
