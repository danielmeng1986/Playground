import {
  ExceptionFilter,
  Catch,
  ArgumentsHost,
  HttpException,
  HttpStatus,
} from '@nestjs/common';
import { Request, Response } from 'express';

interface ErrorResponse {
  message?: string;
  description?: string;
  error?: string;
  [key: string]: unknown;
}

@Catch()
export class AllExceptionsFilter implements ExceptionFilter {
  catch(exception: unknown, host: ArgumentsHost) {
    const ctx = host.switchToHttp();
    const response = ctx.getResponse<Response>();
    const request = ctx.getRequest<Request>();

    let status: number;
    let message: string;
    let description: string;

    if (exception instanceof HttpException) {
      // Handle HTTP exceptions (including 400, 401, 409, 500, etc.)
      status = exception.getStatus();
      const exceptionResponse = exception.getResponse();

      if (typeof exceptionResponse === 'string') {
        message = exceptionResponse;
        description = exceptionResponse;
      } else {
        const responseObj = exceptionResponse as ErrorResponse;
        message = responseObj.message || responseObj.error || 'Error occurred';
        description = responseObj.description || responseObj.message || message;
      }
    } else {
      // Handle unexpected errors (like database connection errors, network errors, etc.)
      status = HttpStatus.INTERNAL_SERVER_ERROR;
      message = 'Internal Server Error';
      description = 'An unexpected error occurred';

      // Print detailed error information in development environment
      if (process.env.NODE_ENV !== 'production') {
        console.error('Unexpected error:', exception);
      }
    }

    const errorResponse = {
      statusCode: status,
      timestamp: new Date().toISOString(),
      path: request.url,
      method: request.method,
      message,
      description,
      error: this.getErrorName(status),
    };

    response.status(status).json(errorResponse);
  }

  private getErrorName(status: number): string {
    switch (status) {
      case 400:
        return 'Bad Request';
      case 401:
        return 'Unauthorized';
      case 403:
        return 'Forbidden';
      case 404:
        return 'Not Found';
      case 409:
        return 'Conflict';
      case 500:
        return 'Internal Server Error';
      default:
        return 'Error';
    }
  }
}
